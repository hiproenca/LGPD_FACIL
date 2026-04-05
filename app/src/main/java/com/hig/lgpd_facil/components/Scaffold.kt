package com.hig.lgpd_facil.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hig.lgpd_facil.navigation.ScreenRoutes

data class ItemMenu(val rota: String, val titulo: String, val icone: ImageVector)

@Composable
fun AppScaffold(
    navController: NavHostController,

    content: @Composable (PaddingValues) -> Unit
) {
    val itensMenu = listOf(
        ItemMenu(ScreenRoutes.Dashboard.route, "Início", Icons.Filled.Home),
        ItemMenu(ScreenRoutes.Legislacao.route, "Radar", Icons.Filled.List),
        ItemMenu(ScreenRoutes.PlanoAcao.route, "Plano", Icons.Filled.CheckCircle),
        ItemMenu(ScreenRoutes.Educativo.route, "Guia", Icons.Filled.Info)
    )

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val rotaAtual = navBackStackEntry?.destination?.route


            if (rotaAtual != ScreenRoutes.Diagnostico.route) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    itensMenu.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icone, contentDescription = item.titulo) },
                            label = { Text(item.titulo) },
                            selected = rotaAtual == item.rota,
                            onClick = {
                                navController.navigate(item.rota) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}

