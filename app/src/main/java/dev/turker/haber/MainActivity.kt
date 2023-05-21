package dev.turker.haber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.turker.haber.article.ArticleList
import dev.turker.haber.ui.theme.HaberAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var articles by remember { mutableStateOf<Array<Article>>(emptyArray()) }

            LaunchedEffect(key1 = articles){
                CoroutineScope(Dispatchers.IO).launch {
                    articles = ApiService().article.getArticles().execute().body()!!
                }
            }

            HaberAppTheme {
                ArticleList(articles)
            }
        }
    }
}