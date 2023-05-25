package dev.turker.haber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

            LaunchedEffect(Unit){
                CoroutineScope(Dispatchers.IO).launch {
                    articles = ApiService().article.getArticles().execute().body()!!
                }
            }

            HaberAppTheme {
                Column {
                    Text("Haber", style = dev.turker.haber.ui.theme.Typography.titleLarge.copy(
                        fontSize = 36.sp,
                        textAlign = TextAlign.Center
                    ), modifier = Modifier.fillMaxWidth().padding(12.dp))
                    Box(Modifier.padding(8.dp)){
                        ArticleList(articles)
                    }
                }
            }
        }
    }
}