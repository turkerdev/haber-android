package dev.turker.haber.article

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.turker.haber.Article
import dev.turker.haber.ui.theme.HaberAppTheme
import dev.turker.haber.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(article: Article){
    val ctx = LocalContext.current
    val limit = 120
    var desc = article.description.take(limit)
    if(article.description.length > limit){
        desc = desc.trim() + "..."
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .shadow(elevation = 6.dp, shape = Shapes.medium),
        onClick = {
        val intent = Intent(ctx, ArticleActivity::class.java)
        intent.putExtra("article", article)
        ctx.startActivity(intent)
    }) {
        Column(Modifier.padding(8.dp)) {
            Text(article.title, style = dev.turker.haber.ui.theme.Typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold
            ))
            Text(desc, Modifier.padding(top = 6.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewArticleCard(){
    val article = Article(
        id = "1",
        title = "Elon Musk to Sell Tesla to Russia",
        description = "Elon Musk, the CEO of Tesla, has announced that he is selling the company to Russia. Musk said that he made the decision after becoming disillusioned with the United States government. He also said that he believes that Russia is a more friendly and welcoming environment for business.")

    HaberAppTheme {
        ArticleCard(article)
    }
}