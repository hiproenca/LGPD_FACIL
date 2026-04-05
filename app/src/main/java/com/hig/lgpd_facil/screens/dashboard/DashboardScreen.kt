package com.hig.lgpd_facil.screens.dashboard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hig.lgpd_facil.R
import com.hig.lgpd_facil.data.local.DiagnosticoEntity
import com.hig.lgpd_facil.ui.theme.Lgpd_FacilTheme

//Statefull
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onIrParaDiagnostico: () -> Unit
) {
    // Escuta o estado do ViewModel
    val diagnostico by viewModel.diagnostico.collectAsState()

    // Carrega os dados ao abrir
    LaunchedEffect(Unit) {
        viewModel.carregarDados()
    }


    DashboardContent(
        diagnostico = diagnostico,
        onIrParaDiagnostico = onIrParaDiagnostico
    )
}

//Statless
@Composable
fun DashboardContent(
    diagnostico: DiagnosticoEntity?,
    onIrParaDiagnostico: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.vis_o_geral_lgpd),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (diagnostico == null) {
                Text(
                    text = stringResource(R.string.sem_dados),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${diagnostico.notaCompliance}%",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (diagnostico.notaCompliance >= 70) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary
                    )
                    Text("Conformidade", style = MaterialTheme.typography.labelLarge)
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onIrParaDiagnostico,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Iniciar Diagnóstico", style = MaterialTheme.typography.labelLarge, color = Color.White)
        }
    }
}

//Mockup preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    Lgpd_FacilTheme {

        DashboardContent(
            diagnostico = DiagnosticoEntity(
                notaCompliance = 85,
                tarefasPendentes = 3
            ),
            onIrParaDiagnostico = {}
        )
    }
}