package com.example.composenewsapp.di

import com.example.composenewsapp.ui.mainscreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainScreenViewModel(get()) }
}
