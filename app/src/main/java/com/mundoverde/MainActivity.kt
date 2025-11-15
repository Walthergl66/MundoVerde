package com.mundoverde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mundoverde.ui.theme.MundoVerdeTheme
import com.mundoverde.ui.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MundoVerdeTheme {
                AppNavGraph()
            }
        }
    }
}
