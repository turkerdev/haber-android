package dev.turker.haber

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.Serializable

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
    val id: String,
    val title: String,
    val description: String
) : Serializable

data class Comment(
    val id: String,
    val comment: String
) : Serializable

data class NewComment(
    val article_id:String,
    val comment:String
):Serializable

interface IArticleRepo{
    @GET("/")
    fun getArticles(@Query("after") articleId:String?): Call<Array<Article>>

    @GET("/search")
    fun findArticles(@Query("q") query:String): Call<Array<Article>>

    @GET("/comments/{id}")
    fun getComments(@Path("id") articleId:String): Call<Array<Comment>>

    @POST("/comment")
    fun postComment(@Body newComment: NewComment): Call<Unit>
}