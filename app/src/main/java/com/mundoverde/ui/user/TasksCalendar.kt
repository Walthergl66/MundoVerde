package com.mundoverde.ui.user

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksCalendar() {
    Scaffold(topBar = { TopAppBar(title = { Text("Calendario de Tareas") }) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text("Vista de calendario - (simulada)")
            Spacer(modifier = Modifier.height(12.dp))
            Text("- 2025-11-10: Podar Tomate")
            Text("- 2025-11-12: Fertilizar Lechuga")
        }
    }
}
