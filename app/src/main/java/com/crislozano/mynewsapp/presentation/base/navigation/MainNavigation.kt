package com.crislozano.mynewsapp.presentation.base.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.crislozano.mynewsapp.presentation.detailscreen.DetailsScreen
import com.crislozano.mynewsapp.presentation.listscreen.ListScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainNavScreens.LIST.name){
        composable (route = MainNavScreens.LIST.name) {
            ListScreen(navController)
        }
        composable (route = MainNavScreens.DETAILS.name+"/{newsId}") {
            DetailsScreen(navController, it.arguments?.getString("newsId") ?: "")
        }
    }
}