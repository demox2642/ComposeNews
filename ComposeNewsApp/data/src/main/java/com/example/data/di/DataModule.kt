package com.example.data.di

import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import org.koin.dsl.module

val dataModule = module {
    single <NewsRepository> { NewsRepositoryImpl() }
}
