package com.example.data.network

import com.example.domain.models.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNewsList(
        @Query("q") query: String = "android",
        @Query("language") language: String = "ru",
        @Query("page") page: Int = 1
    ): ArticleResponse
}
