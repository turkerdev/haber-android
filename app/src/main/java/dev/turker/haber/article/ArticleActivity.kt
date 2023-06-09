package dev.turker.haber.article

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.turker.haber.ApiService
import dev.turker.haber.Article
import dev.turker.haber.Comment
import dev.turker.haber.main.MainActivity
import dev.turker.haber.ui.theme.HaberAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val article = intent.extras?.getSerializable("article") as Article
            var comments by remember { mutableStateOf<Array<Comment>>(emptyArray()) }
            var isCommentsLoading by remember {mutableStateOf(true)}

            fun fetchComments() {
                CoroutineScope(Dispatchers.IO).launch {
                    ApiService().article.getComments(article.id).enqueue(object:
                        Callback<Array<Comment>> {

                        override fun onResponse(
                            call: Call<Array<Comment>>,
                            response: Response<Array<Comment>>
                        ) {
                            comments = response.body()!!
                            isCommentsLoading = false
                        }

                        override fun onFailure(call: Call<Array<Comment>>, t: Throwable) {
                            TODO("Not yet implemented")
                            isCommentsLoading = false
                        }
                    })
                }
            }

            LaunchedEffect(Unit){
                fetchComments()
            }

            val intent = Intent(LocalContext.current, MainActivity::class.java)

            HaberAppTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {
                        IconButton(onClick = {
                            navigateUpTo(intent)
                        }) {
                            Icon(Icons.Rounded.ArrowBack, "")
                        }
                    })
                }) { contentPadding ->
                    Box(Modifier.padding(contentPadding)){
                        Column{
                            ArticleDetail(article)
                            Divider()
                            Box(modifier = Modifier.padding(8.dp,0.dp)){
                                CommentWrite(article.id, { fetchComments() })
                            }
                            if(!isCommentsLoading){
                                Box(Modifier.padding(8.dp)){
                                    CommentList(comments)
                                }
                            } else {
                                Box(modifier = Modifier.fillMaxWidth().padding(8.dp)){
                                    LinearProgressIndicator(Modifier.align(Alignment.Center))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}