package dev.turker.haber.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.turker.haber.Article

@Composable
fun ArticleList(articles: List<Article>, loading:Boolean, fetchMore: ()->Unit){
    val listState = rememberLazyListState()

    LaunchedEffect(listState.canScrollForward){
        if(!listState.canScrollForward){
            fetchMore()
        }
    }

    if(loading){
        Box(modifier = Modifier.fillMaxWidth().zIndex(2f)){
            LinearProgressIndicator(Modifier.align(Center))
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState, modifier = Modifier.padding(8.dp, 0.dp)
    ) {
        items(articles){ article ->
            ArticleCard(article)
        }
    }
}