package dev.turker.haber.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.turker.haber.Article
import dev.turker.haber.ui.theme.HaberAppTheme

@Composable
fun ArticleCard(article: Article){
    val limit = 120
    var desc = article.text.take(limit)
    if(article.text.length > limit){
        desc = desc.trim() + "..."
    }

    Column(Modifier.padding(8.dp)) {
        Text(article.title, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Text(desc, Modifier.padding(top = 6.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewArticleCard(){
    val article = Article(
        id = 1,
        title = "Elon Musk to Sell Tesla to Russia",
        text = "Elon Musk, the CEO of Tesla, has announced that he is selling the company to Russia. Musk said that he made the decision after becoming disillusioned with the United States government. He also said that he believes that Russia is a more friendly and welcoming environment for business.")

    HaberAppTheme {
        ArticleCard(article)
    }
}