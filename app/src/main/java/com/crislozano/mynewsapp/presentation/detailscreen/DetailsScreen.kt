package com.crislozano.mynewsapp.presentation.detailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.crislozano.mynewsapp.R
import com.crislozano.mynewsapp.presentation.base.components.ErrorScreen
import com.crislozano.mynewsapp.presentation.base.components.LoadingScreen
import com.crislozano.mynewsapp.presentation.base.components.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(navController: NavController, newsId: String, viewModel: DetailsScreenViewModel = koinViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(stringResource(R.string.title_news_detail),
            backButton = true,
            onBack = { navController.popBackStack() },
            )}
        ) { innerPadding ->

        val state by viewModel.state.collectAsState()
        LaunchedEffect(true) {
            viewModel.handleIntent(DetailsScreenIntent.RequestNew(newsId))
        }

        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when {
                state.isLoading -> LoadingScreen()
                state.error != null -> ErrorScreen(retry = { viewModel.handleIntent(DetailsScreenIntent.RequestNew(newsId)) }, error = state.error ?: stringResource(R.string.default_error_text))
                else -> {
                    if(state.news != null) {
                        NewsDetails(news = state.news!!)
                    } else{
                        ErrorScreen(retry = { viewModel.handleIntent(DetailsScreenIntent.RequestNew(newsId)) }, error = stringResource(R.string.default_error_text))
                    }
                }
            }
        }
    }

}
