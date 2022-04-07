package com.example.domain.di

import com.example.domain.usecase.GetNewsList
import org.koin.dsl.module

val domainModule = module {
    single { GetNewsList(get()) }
}
