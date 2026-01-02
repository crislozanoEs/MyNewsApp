package com.crislozano.mynewsapp.presentation.di

import com.crislozano.mynewsapp.presentation.detailscreen.DetailsScreenViewModel
import com.crislozano.mynewsapp.presentation.listscreen.ListsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ListsScreenViewModel(get()) }
    viewModel { DetailsScreenViewModel(get()) }
}