package com.crislozano.mynewsapp.presentation.detailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.crislozano.mynewsapp.R
import com.crislozano.mynewsapp.domain.model.NewsDetails
import com.crislozano.mynewsapp.presentation.base.toPresentationDate

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetails(news: NewsDetails) {
    Box(
        Modifier.fillMaxSize().padding(8.dp)
    ){
        Column {
            Text(
                text = news.publishAt.toPresentationDate(),
                modifier = Modifier.padding(8.dp).align(Alignment.End),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = news.title,
                modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = news.description,
                modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium,
            )
            GlideImage(
                model = news.imageUrl,
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RectangleShape),
                failure = placeholder {
                    Box(modifier = Modifier.wrapContentSize()){
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "News Icon",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(8.dp)
                                .clip(RectangleShape),
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            )
            Text(
                text = news.snippet,
                modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = stringResource(R.string.source_label_text, news.source),
                modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}