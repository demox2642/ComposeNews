package com.example.composenewsapp.ui.mainscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.composenewsapp.ui.screenstate.ErrorItem
import com.example.composenewsapp.ui.screenstate.LoadingItem
import com.example.composenewsapp.ui.screenstate.LoadingView
import com.example.composenewsapp.ui.theme.AppTheme
import com.example.domain.models.News
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainScreenViewModel = get()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Android News", color = AppTheme.colors.systemTextPrimary) },
                backgroundColor = AppTheme.colors.systemBackgroundPrimary,

            )
        },
        backgroundColor = AppTheme.colors.systemBackgroundTertiary,
        content = {
            NewsList(movies = viewModel.newsList)
        }
    )
}

@Composable
fun NewsList(movies: Flow<PagingData<News>>) {
    val lazyMovieItems = movies.collectAsLazyPagingItems()

    LazyColumn {

        items(lazyMovieItems) { news ->
            NewsItem(news = news!!)
        }

        lazyMovieItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NewsItem(news: News) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier.height(200.dp)

        ) {
            GlideImage(
                imageModel = news.urlToImage,
                modifier = Modifier.fillMaxSize(),
                loading = {
                    Box(modifier = Modifier.matchParentSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                contentScale = ContentScale.Crop,
                failure = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(com.example.composenewsapp.R.drawable.ic_no_image),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

            )
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (description, title) = createRefs()

                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.White.copy(.8f))
                        .height(50.dp)
                        .constrainAs(title) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = news.title,
                        modifier = Modifier.padding(16.dp),
                        maxLines = 1,
                        color = AppTheme.colors.controlTextBlueDark,
                        style = AppTheme.typography.h5,
                        textAlign = TextAlign.End
                    )
                }

                if (news.description.isNotEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .background(AppTheme.colors.colorGraphPink.copy(.7f))
                            .height(70.dp)
                            .constrainAs(description) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = news.description,
                            modifier = Modifier.padding(16.dp),
                            maxLines = 2,
                            color = AppTheme.colors.systemTextOnPrimary,
                            style = AppTheme.typography.body1,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
    }
}
