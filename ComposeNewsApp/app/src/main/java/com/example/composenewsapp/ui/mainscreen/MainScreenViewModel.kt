package com.example.composenewsapp.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.data.paget.NewsPaget
import com.example.domain.usecase.GetNewsList

class MainScreenViewModel(
    private val repositoryImpl: GetNewsList
) : ViewModel() {

    val newsList = Pager(PagingConfig(pageSize = 20)) {
        NewsPaget(repositoryImpl)
    }.flow
}
