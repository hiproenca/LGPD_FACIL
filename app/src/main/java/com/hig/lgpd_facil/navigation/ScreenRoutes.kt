package com.hig.lgpd_facil.navigation

sealed class ScreenRoutes(val route: String) {
    object Dashboard : ScreenRoutes("dashboard")
    object Diagnostico : ScreenRoutes("diagnostico")
    object PlanoAcao : ScreenRoutes("plano_acao")
    object Legislacao : ScreenRoutes("legislacao")
    object Educativo : ScreenRoutes("educativo")

}