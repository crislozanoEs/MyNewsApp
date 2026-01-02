package com.crislozano.mynewsapp.presentation.listscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.crislozano.mynewsapp.domain.model.NewsSummary
import com.crislozano.mynewsapp.presentation.base.navigation.MainNavScreens
import com.crislozano.mynewsapp.presentation.base.toPresentationDate

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItem(news: NewsSummary, navController: NavController){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        onClick = { navController.navigate(route = "${MainNavScreens.DETAILS}/${news.uuid}") }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column {
                Text(
                    text = news.title,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleMedium,
                )
                GlideImage(
                    model = news.imageUrl,
                    contentDescription = "News Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RectangleShape)
                        .align(Alignment.CenterHorizontally),
                    failure = placeholder {
                        Box(modifier = Modifier.wrapContentSize()){
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "News Icon",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clip(RectangleShape),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                )
                Text(
                    text = news.publishAt.toPresentationDate(),
                    modifier = Modifier.align(Alignment.End).padding(end = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = news.description,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.Start),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}