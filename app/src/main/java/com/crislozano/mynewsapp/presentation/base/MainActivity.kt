package com.crislozano.mynewsapp.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.crislozano.mynewsapp.presentation.base.navigation.MainNavigation
import com.crislozano.mynewsapp.presentation.base.theme.ui.MyNewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNewsApp {
                MainNavigation()
            }
        }
    }
}

@Composable
fun MyNewsApp(content: @Composable () -> Unit) {
    MyNewsAppTheme {
        content()
    }
}