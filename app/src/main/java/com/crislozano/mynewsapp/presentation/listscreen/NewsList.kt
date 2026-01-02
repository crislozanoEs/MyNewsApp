package com.crislozano.mynewsapp.presentation.listscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.crislozano.mynewsapp.domain.model.NewsSummary
import com.crislozano.mynewsapp.presentation.base.components.LoadMoreButton

@Composable
fun NewsList(news: List<NewsSummary>, navController: NavController, loadMore: () -> Unit, hasMore: Boolean, isLoadingMore: Boolean) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        items(news.size) { index ->
            NewsItem(news = news[index], navController = navController)
        }
        item {
            if(hasMore) {
                LoadMoreButton(isLoadingMore = isLoadingMore, onClick = { loadMore() })
            }
        }
    }
}