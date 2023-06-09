package dev.turker.haber.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.turker.haber.ApiService
import dev.turker.haber.Article
import dev.turker.haber.article.ArticleList
import dev.turker.haber.ui.theme.HaberAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var recentArticles = remember { mutableStateListOf<Article>() }
            var fetchingRecentArticles by remember { mutableStateOf(false) }
            var searchedArticles = remember { mutableStateListOf<Article>() }
            var fetchingSearchedArticles by remember { mutableStateOf(false) }
            var isSearching by remember { mutableStateOf(false) }

            fun fetchRecentArticles(){
                if(!fetchingRecentArticles){
                    fetchingRecentArticles = true
                    CoroutineScope(Dispatchers.IO).launch {
                        ApiService()
                            .article
                            .getArticles(recentArticles.lastOrNull()?.id)
                            .enqueue(object: Callback<Array<Article>> {
                                override fun onResponse(
                                    call: Call<Array<Article>>,
                                    response: Response<Array<Article>>
                                ) {
                                    response.body()?.forEach { recentArticles.add(it) }
                                    fetchingRecentArticles = false;
                                }

                                override fun onFailure(call: Call<Array<Article>>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }
                        })
                    }
                }
            }

            fun onQueryChange(query:String){
                if(query.isEmpty()){
                    isSearching = false
                    fetchingSearchedArticles = false
                }
                else {
                    fetchingSearchedArticles = true
                    isSearching = true
                    searchedArticles.clear()
                    CoroutineScope(Dispatchers.IO).launch {
                        ApiService()
                            .article
                            .findArticles(query)
                            .enqueue(object: Callback<Array<Article>> {
                                override fun onResponse(
                                    call: Call<Array<Article>>,
                                    response: Response<Array<Article>>
                                ) {
                                    response.body()?.forEach { searchedArticles.add(it) }
                                    fetchingSearchedArticles = false
                                }

                                override fun onFailure(call: Call<Array<Article>>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }
                            })
                    }
                }
            }

            LaunchedEffect(Unit){
                fetchRecentArticles()
            }

            HaberAppTheme {
                Scaffold(
                    topBar = { MainTopBar(onQueryChange = {onQueryChange(it)}) }) {
                    Surface(modifier = Modifier
                        .padding(it)
                        .padding(start = 16.dp, end = 16.dp)) {
                        if(isSearching){
                            ArticleList(
                                articles = searchedArticles,
                                fetchMore = {},
                                loading = fetchingSearchedArticles
                            )
                        }else {
                            ArticleList(
                                articles = recentArticles,
                                fetchMore = {fetchRecentArticles()},
                                loading = fetchingRecentArticles
                            )
                        }
                    }
                }
            }
        }
    }
}