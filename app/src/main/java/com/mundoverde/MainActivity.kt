package com.mundoverde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mundoverde.ui.theme.MundoVerdeTheme
import com.mundoverde.ui.navigation.AppNavGraph
import com.mundoverde.utils.LifecycleLogger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MundoVerdeTheme {
                // Log ciclo de vida a nivel de actividad/raíz
                LifecycleLogger(tag = "MainActivity")
                AppNavGraph()
            }
        }
    }
}
