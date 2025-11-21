package com.mundoverde.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun LifecycleLogger(
    tag: String,
    onEvent: ((Lifecycle.Event) -> Unit)? = null
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> Log.d(tag, "onCreate")
                Lifecycle.Event.ON_START -> Log.d(tag, "onStart")
                Lifecycle.Event.ON_RESUME -> Log.d(tag, "onResume")
                Lifecycle.Event.ON_PAUSE -> Log.d(tag, "onPause")
                Lifecycle.Event.ON_STOP -> Log.d(tag, "onStop")
                Lifecycle.Event.ON_DESTROY -> Log.d(tag, "onDestroy")
                else -> { /* ignore */ }
            }
            onEvent?.invoke(event)
        }
        val lifecycle = lifecycleOwner.lifecycle
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
}
