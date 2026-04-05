package com.hig.lgpd_facil.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import com.hig.lgpd_facil.components.AppScaffold


import com.hig.lgpd_facil.screens.dashboard.DashboardScreen
import com.hig.lgpd_facil.screens.dashboard.DashboardViewModel
import com.hig.lgpd_facil.screens.diagnostico.DiagnosticoScreen
import com.hig.lgpd_facil.screens.diagnostico.DiagnosticoViewModel
import com.hig.lgpd_facil.screens.educativo.EducativoScreen
import com.hig.lgpd_facil.screens.legislacao.LegislacaoScreen
import com.hig.lgpd_facil.screens.legislacao.LegislacaoViewModel
import com.hig.lgpd_facil.screens.plano_acao.PlanoModel
import com.hig.lgpd_facil.screens.plano_acao.PlanoScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    dashboardViewModel: DashboardViewModel,
    diagnosticoViewModel: DiagnosticoViewModel,
    legislacaoViewModel: LegislacaoViewModel,
    planoModel: PlanoModel
) {

    AppScaffold(navController = navController) { innerPadding ->


        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.Dashboard.route,

            modifier = Modifier.padding(innerPadding)
        ) {


            composable(ScreenRoutes.Dashboard.route) {
                DashboardScreen(
                    viewModel = dashboardViewModel,
                    onIrParaDiagnostico = { navController.navigate(ScreenRoutes.Diagnostico.route) }
                )
            }


            composable(ScreenRoutes.Diagnostico.route) {
                DiagnosticoScreen(
                    viewModel = diagnosticoViewModel,
                    onFinalizar = {

                        dashboardViewModel.carregarDados()
                        navController.popBackStack()
                    }
                )
            }


            composable(ScreenRoutes.Legislacao.route) {
                LegislacaoScreen(viewModel = legislacaoViewModel)
            }


            composable(ScreenRoutes.PlanoAcao.route) {
                PlanoScreen(viewModel = planoModel)
            }


            composable(ScreenRoutes.Educativo.route) {
                EducativoScreen()
            }
        }
    }
}