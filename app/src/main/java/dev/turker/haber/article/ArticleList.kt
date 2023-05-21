package dev.turker.haber.article

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.turker.haber.Article

@Composable
fun ArticleList(articles: Array<Article>){
    LazyColumn {
        items(articles){ article ->
            ArticleCard(article)
            Divider()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewArticleList(){
    val fakeArticles = arrayOf(
        Article(1,"title 1","desc 1"),
        Article(2, "title 2","desc 2")
    )

    ArticleList(fakeArticles)
}