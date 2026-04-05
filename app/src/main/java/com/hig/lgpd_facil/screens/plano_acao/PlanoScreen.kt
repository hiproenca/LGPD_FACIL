package com.hig.lgpd_facil.screens.plano_acao

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hig.lgpd_facil.R
import com.hig.lgpd_facil.model.TarefaPlano
import com.hig.lgpd_facil.ui.theme.Lgpd_FacilTheme

//Statefull
@Composable
fun PlanoScreen(viewModel: PlanoModel) {

    val tarefas by viewModel.tarefas.collectAsState()


    PlanoContent(
        tarefas = tarefas,
        onTarefaAlternada = { id, concluida ->

            viewModel.alternarTarefa(id, concluida)
        }
    )
}

//Stateless
@Composable
fun PlanoContent(
    tarefas: List<TarefaPlano>,
    onTarefaAlternada: (Int, Boolean) -> Unit
) {
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
                text = "Plano de Ação",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }


        Text(
            text = "Ações práticas para adequar sua empresa à LGPD.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tarefas) { tarefa ->

                CardTarefa(
                    tarefa = tarefa,
                    onCheckedChange = { isChecked ->
                        onTarefaAlternada(tarefa.id, isChecked)
                    }
                )
            }
        }
    }
}


@Composable
fun CardTarefa(
    tarefa: TarefaPlano,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = tarefa.completada,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.secondary) // Verde
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = stringResource(id = tarefa.descricao),
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = if (tarefa.completada) TextDecoration.LineThrough else TextDecoration.None,
                color = if (tarefa.completada) Color.Gray else MaterialTheme.colorScheme.onSurface
            )

        }
    }
}

//mockup
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlanoPreview() {

    Lgpd_FacilTheme {

        val tarefasFakes = listOf(
            TarefaPlano(1, R.string.tarefa_1, completada = true),
            TarefaPlano(2, R.string.tarefa_2, completada = false),
            TarefaPlano(3, R.string.tarefa_3, completada = false)
        )


        PlanoContent(
            tarefas = tarefasFakes,
            onTarefaAlternada = { _, _ -> }
        )
    }
}