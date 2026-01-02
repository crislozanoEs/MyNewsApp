package com.crislozano.mynewsapp.domain.di

import com.crislozano.mynewsapp.domain.usecases.GetNewsDetailsUC
import com.crislozano.mynewsapp.domain.usecases.GetNewsSummaryUC
import com.crislozano.mynewsapp.domain.usecases.IGetNewsDetailsUC
import com.crislozano.mynewsapp.domain.usecases.IGetNewsSummaryUC
import org.koin.dsl.module

val domainModule = module {
    single<IGetNewsSummaryUC>  { GetNewsSummaryUC(get()) }
    single<IGetNewsDetailsUC>  { GetNewsDetailsUC(get()) }

}