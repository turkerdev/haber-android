package dev.turker.haber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.turker.haber.article.ArticleDetail
import dev.turker.haber.article.CommentList
import dev.turker.haber.article.CommentWrite
import dev.turker.haber.ui.theme.HaberAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleActivity : ComponentActivity() {
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

            HaberAppTheme {
                Column{
                    ArticleDetail(article)
                    CommentWrite(article.id, { fetchComments() })
                    if(!isCommentsLoading){
                        CommentList(comments)
                    } else {
                        Text("Loading...")
                    }
                }
            }
        }
    }
}