package dev.turker.haber

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiService {
    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://fake-news.turker.dev")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val article = retrofit
        .create(IArticleRepo::class.java)
}

data class Article(
    val id: Int,
    val title: String,
    val text: String
)

interface IArticleRepo{
    @GET("/")
    fun getArticles(): Call<Array<Article>>
}