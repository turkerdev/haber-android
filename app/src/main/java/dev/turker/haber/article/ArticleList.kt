package dev.turker.haber.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.turker.haber.Article
import dev.turker.haber.ui.theme.HaberAppTheme

@Composable
fun ArticleList(articles: Array<Article>){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(articles){ article ->
            ArticleCard(article)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewArticleList(){
    val fakeArticles = arrayOf(
        Article("1","title 1","desc 1"),
        Article("2", "title 2","desc 2")
    )

    HaberAppTheme {
        ArticleList(fakeArticles)
    }
}