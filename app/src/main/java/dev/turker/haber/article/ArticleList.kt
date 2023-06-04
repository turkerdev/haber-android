package dev.turker.haber.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.turker.haber.ApiService
import dev.turker.haber.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ArticleList(){
    val listState = rememberLazyListState()
    var articles = remember { mutableStateListOf<Article>() }
    var isFetching by remember {mutableStateOf(false)}

    fun fetchArticles(){
        CoroutineScope(Dispatchers.IO).launch {
            if(!isFetching){
                isFetching = true
                ApiService().article.getArticles(articles.lastOrNull()?.id).enqueue(object:
                    Callback<Array<Article>> {

                    override fun onResponse(
                        call: Call<Array<Article>>,
                        response: Response<Array<Article>>
                    ) {
                        isFetching = false;
                        response.body()?.forEach { articles.add(it) }
                    }

                    override fun onFailure(call: Call<Array<Article>>, t: Throwable) {
                        isFetching = false;
                    }
                })

            }
        }
    }

    LaunchedEffect(Unit){
        fetchArticles()
    }

    LaunchedEffect(listState.canScrollForward){
        if(!listState.canScrollForward){
            fetchArticles()
        }
    }

    if(isFetching){
        Box(modifier = Modifier.fillMaxWidth()){
            LinearProgressIndicator(Modifier.align(Center))
        }
    }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), state = listState, modifier = Modifier.padding(8.dp, 0.dp)) {
        items(articles){ article ->
            ArticleCard(article)
        }
    }
}