package com.crislozano.mynewsapp.presentation.listscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.crislozano.mynewsapp.R
import com.crislozano.mynewsapp.presentation.base.components.EmptyScreen
import com.crislozano.mynewsapp.presentation.base.components.ErrorScreen
import com.crislozano.mynewsapp.presentation.base.components.LoadingScreen
import com.crislozano.mynewsapp.presentation.base.components.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(navController: NavController, viewModel: ListsScreenViewModel = koinViewModel()){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(stringResource(R.string.title_list_of_news),
            backButton = false,
            actions = {
                IconButton(
                    onClick = { viewModel.handleIntent(ListScreenIntent.RequestNews)}
                ) {
                    Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "Refresh"
                )
            }
        })}
    ) { innerPadding ->

        val state by viewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            if(state.news.isEmpty()) viewModel.handleIntent(ListScreenIntent.RequestNews)
        }

        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when {
                state.isLoading -> LoadingScreen()
                state.error != null -> ErrorScreen(retry = { viewModel.handleIntent(ListScreenIntent.RequestNews) }, error = state.error ?: stringResource(R.string.default_error_text))
                state.isEmpty -> EmptyScreen(retry = { viewModel.handleIntent(ListScreenIntent.RequestNews) }, message = stringResource(R.string.default_empty_text))
                else -> {
                    NewsList(news = state.news, navController = navController,
                        loadMore = { viewModel.handleIntent(ListScreenIntent.LoadMore) }, hasMore = state.hasMore, isLoadingMore = state.isLoadingMore)
                }
            }
        }
    }
}