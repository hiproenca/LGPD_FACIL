package com.hig.lgpd_facil.screens.plano_acao


import androidx.lifecycle.ViewModel
import com.hig.lgpd_facil.R
import com.hig.lgpd_facil.model.TarefaPlano
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class PlanoModel : ViewModel() {


    private val _tarefas = MutableStateFlow(
        listOf(
            TarefaPlano(1, R.string.tarefa_1),
            TarefaPlano(2, R.string.tarefa_2),
            TarefaPlano(3, R.string.tarefa_3),
            TarefaPlano(4, R.string.tarefa_4),
            TarefaPlano(5, R.string.tarefa_5)
        )
    )
    val tarefas: StateFlow<List<TarefaPlano>> = _tarefas.asStateFlow()

    fun alternarTarefa(tarefaId: Int, concluida: Boolean) {
//muda valor de tarefae
        _tarefas.update { listaAtual ->
            listaAtual.map { tarefa ->
                if (tarefa.id == tarefaId) {
                    tarefa.copy(completada = concluida)
                } else {
                    tarefa
                }
            }
        }
    }
}