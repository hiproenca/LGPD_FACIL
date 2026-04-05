package com.hig.lgpd_facil.screens.legislacao

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hig.lgpd_facil.model.Lei
import com.hig.lgpd_facil.components.CardLei
import com.hig.lgpd_facil.ui.theme.Lgpd_FacilTheme

@Composable
fun LegislacaoScreen(viewModel: LegislacaoViewModel) {
    val leis by viewModel.leis.collectAsState()
    val carregando by viewModel.carregando.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.carregarLeis()
    }

    LegislacaoContent(leis = leis, carregando = carregando)
}

@Composable
fun LegislacaoContent(leis: List<Lei>, carregando: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Radar Legislativo",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }


        if (carregando) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else if (leis.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Nenhuma lei encontrada ou\nsem conexão com a internet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(leis) { lei ->

                    CardLei(lei = lei)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LegislacaoPreview() {
    Lgpd_FacilTheme {
        val leisFakes = listOf(
            Lei(1, "PL", 1234, 2023, "Altera a Lei Geral de Proteção de Dados Pessoais para dispor sobre regras claras de consentimento em aplicativos mobile."),
            Lei(2, "PL", 5678, 2024, "Regulamenta o uso de inteligência artificial na manipulação de dados sensíveis de usuários na internet.")
        )
        LegislacaoContent(leis = leisFakes, carregando = false)
    }
}