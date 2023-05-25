package dev.turker.haber

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import dev.turker.haber.article.ArticleDetail
import dev.turker.haber.article.ArticleList
import dev.turker.haber.article.CommentList
import dev.turker.haber.article.CommentWrite
import dev.turker.haber.ui.theme.HaberAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val article = intent.extras?.getSerializable("article") as Article
            var comments by remember { mutableStateOf<Array<Comment>>(emptyArray()) }

            LaunchedEffect(Unit){
                CoroutineScope(Dispatchers.IO).launch {
                    comments = ApiService().article.getComments(article.id).execute().body()!!
                }
            }

            HaberAppTheme {
                Column{
                    ArticleDetail(article)
                    CommentWrite(article.id)
                    CommentList(comments)
                }
            }
        }
    }
}