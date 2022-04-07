package com.example.data.repository

import com.example.data.network.NetworkManager
import com.example.domain.models.ArticleResponse
import com.example.domain.repository.NewsRepository

class NewsRepositoryImpl : NewsRepository {

    override suspend fun getNewsList(page: Int): ArticleResponse {
        return NetworkManager.apiService.getNewsList()
    }


}
