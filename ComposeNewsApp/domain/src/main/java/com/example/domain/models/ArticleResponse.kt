package com.example.domain.models

data class ArticleResponse(
    val totalResults: Int,
    val articles: List<News>
)
