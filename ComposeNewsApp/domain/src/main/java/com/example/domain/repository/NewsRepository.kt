package com.example.domain.repository

import com.example.domain.models.ArticleResponse

interface NewsRepository {
    suspend fun getNewsList(page: Int): ArticleResponse
}
