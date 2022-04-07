package com.example.data.paget

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.models.News
import com.example.domain.usecase.GetNewsList

class NewsPaget(
    private val useCase: GetNewsList
) : PagingSource<Int, News>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {

        return try {
            val nextPage = params.key ?: 1
            val newsListResponse = useCase.getNews(nextPage)
            Log.e("NewsPaget", "$nextPage")
            PagingSource.LoadResult.Page(
                data = newsListResponse.articles,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return null
    }
}
