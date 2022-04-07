package com.example.domain.repository

import com.example.domain.models.ArticleResponse
import com.example.domain.models.News

interface NewsRepository {
    suspend fun getNewsList(page:Int): ArticleResponse
}
