package com.mundoverde.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings() {
    Scaffold(topBar = { TopAppBar(title = { Text("Ajustes") }) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text("Configuración general (simulada)")
            Spacer(modifier = Modifier.height(12.dp))
            Text("- Notificaciones: Activadas")
            Text("- Unidades: Métricas")
        }
    }
}
