package com.mundoverde.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskItem(title: String, subtitle: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = subtitle)
    }
}
