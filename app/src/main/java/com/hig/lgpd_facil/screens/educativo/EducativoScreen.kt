package com.hig.lgpd_facil.screens.educativo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hig.lgpd_facil.R

import com.hig.lgpd_facil.components.CardDica
import com.hig.lgpd_facil.ui.theme.Lgpd_FacilTheme


@Composable
fun EducativoScreen() {
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
                text = "Guia Prático LGPD",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            CardDica(
                titulo = stringResource(R.string.o_que_a_lgpd),
                texto = stringResource(R.string.o_que_a_lgpd_resposta)
            )

            CardDica(
                titulo = stringResource(R.string.o_que_s_o_dados_pessoais),
                texto = stringResource(R.string.o_que_s_o_dados_pessoais_resposta)
            )

            CardDica(
                titulo = stringResource(R.string.o_papel_do_dpo),
                texto = stringResource(R.string.o_papel_do_dpo_resposta)
            )

            CardDica(
                titulo = stringResource(R.string.dica_de_ouro),
                texto = stringResource(R.string.dica_de_ouro_resposta)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EducativoPreview() {
    Lgpd_FacilTheme {
        EducativoScreen()
    }
}