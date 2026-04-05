package com.hig.lgpd_facil.screens.diagnostico
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hig.lgpd_facil.R
import com.hig.lgpd_facil.ui.theme.Lgpd_FacilTheme

//Statefull
@Composable
fun DiagnosticoScreen(
    viewModel: DiagnosticoViewModel,
    onFinalizar: () -> Unit
) {

    val respostas by viewModel.respostas.collectAsState()


    val perguntas = viewModel.perguntas


    DiagnosticoContent(
        perguntas = perguntas,
        respostas = respostas,
        onResponder = { perguntaId, resposta ->
            viewModel.responder(perguntaId, resposta)
        },
        onFinalizarClicado = {

            onFinalizar()


            viewModel.finalizarDiagnostico { }
        }
    )
}

//Stateless
@Composable
fun DiagnosticoContent(
    perguntas: List<Pergunta>,
    respostas: Map<Int, Boolean>,
    onResponder: (Int, Boolean) -> Unit,
    onFinalizarClicado: () -> Unit
) {

    val tudoRespondido = respostas.size == perguntas.size

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
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.maturidade),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }


        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(perguntas) { pergunta ->
                val respostaAtual = respostas[pergunta.id]

                CardPergunta(
                    pergunta = pergunta,
                    resposta = respostaAtual,
                    onRespostaChanged = { novaResposta ->
                        onResponder(pergunta.id, novaResposta)
                    }
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onFinalizarClicado,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = tudoRespondido,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (tudoRespondido) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
            ) {
                Text(
                    text = stringResource(R.string.calcular_nota),
                    style = MaterialTheme.typography.labelLarge,
                    color = if (tudoRespondido) Color.White else Color.Gray
                )
            }
        }
    }
}


@Composable
fun CardPergunta(
    pergunta: Pergunta,
    resposta: Boolean?,
    onRespostaChanged: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "${pergunta.id}. ${stringResource(id = pergunta.texto)}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = resposta == true,
                        onClick = { onRespostaChanged(true) }
                    )
                    Text("Sim", modifier = Modifier.padding(start = 4.dp))
                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = resposta == false,
                        onClick = { onRespostaChanged(false) }
                    )
                    Text("Não", modifier = Modifier.padding(start = 4.dp))
                }
            }
        }
    }
}

//mockup
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiagnosticoPreview() {

    Lgpd_FacilTheme {

        val perguntasFake = listOf(
            Pergunta(1, R.string.pergunta_1, R.string.acao_1),
            Pergunta(2, R.string.pergunta_2, R.string.acao_2),
            Pergunta(3, R.string.pergunta_3, R.string.acao_3)
        )


        val respostasFake = mapOf(
            1 to true,
            2 to false
        )


        DiagnosticoContent(
            perguntas = perguntasFake,
            respostas = respostasFake,
            onResponder = { _, _ -> },
            onFinalizarClicado = {}
        )
    }
}