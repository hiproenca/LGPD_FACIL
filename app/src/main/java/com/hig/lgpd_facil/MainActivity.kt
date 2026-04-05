package com.hig.lgpd_facil
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hig.lgpd_facil.data.local.AppDatabase
import com.hig.lgpd_facil.data.repository.LgpdRepository
import com.hig.lgpd_facil.navigation.AppNavigation
import com.hig.lgpd_facil.screens.dashboard.DashboardViewModel
import com.hig.lgpd_facil.screens.diagnostico.DiagnosticoViewModel
import com.hig.lgpd_facil.screens.legislacao.LegislacaoViewModel
import com.hig.lgpd_facil.screens.plano_acao.PlanoModel
import com.hig.lgpd_facil.ui.theme.Lgpd_FacilTheme // Ajustado para o nome do seu tema que vi na imagem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val database = AppDatabase.getDatabase(this)
        val dao = database.diagnosticoDao()
        val repository = LgpdRepository(dao)


        val dashboardViewModel = DashboardViewModel(repository)
        val diagnosticoViewModel = DiagnosticoViewModel(repository)
        val legislacaoViewModel = LegislacaoViewModel(repository)
        val planoModel = PlanoModel()



        setContent {
            Lgpd_FacilTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()


                    AppNavigation(
                        navController = navController,
                        dashboardViewModel = dashboardViewModel,
                        diagnosticoViewModel = diagnosticoViewModel,
                        legislacaoViewModel = legislacaoViewModel,
                        planoModel = planoModel
                    )
                }
            }
        }
    }
}
