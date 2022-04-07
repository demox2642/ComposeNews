package com.example.domain.usecase

import com.example.domain.models.ArticleResponse
import com.example.domain.repository.NewsRepository

class GetNewsList(private val newsRepository: NewsRepository) {
    suspend fun getNews(page: Int): ArticleResponse {
        return newsRepository.getNewsList(page = page)
    }
}
