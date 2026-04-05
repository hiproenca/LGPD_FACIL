package com.hig.lgpd_facil.screens.diagnostico
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hig.lgpd_facil.R
import com.hig.lgpd_facil.data.local.DiagnosticoEntity
import com.hig.lgpd_facil.data.repository.LgpdRepository
import com.hig.lgpd_facil.model.TarefaPlano
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


data class Pergunta(
    val id: Int,
    @StringRes val texto: Int,
    @StringRes val sugestaoAcao: Int
)

class DiagnosticoViewModel(private val repository: LgpdRepository) : ViewModel() {


    val perguntas = listOf(
        Pergunta(1, R.string.pergunta_1,R.string.acao_1),
        Pergunta(2, R.string.pergunta_2, R.string.acao_2),
        Pergunta(3, R.string.pergunta_3, R.string.acao_3),
        Pergunta(4, R.string.pergunta_4, R.string.acao_4),
        Pergunta(5, R.string.pergunta_5, R.string.acao_5)
    )


    private val _respostas = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val respostas: StateFlow<Map<Int, Boolean>> = _respostas.asStateFlow()


    fun responder(perguntaId: Int, resposta: Boolean) {
        val mapaAtualizado = _respostas.value.toMutableMap()
        mapaAtualizado[perguntaId] = resposta
        _respostas.value = mapaAtualizado
    }

    //calculo diagnostico
    fun finalizarDiagnostico(onFinalizado: () -> Unit) {
        viewModelScope.launch {
            val totalPerguntas = perguntas.size
            val respostasDadas = _respostas.value


            if (respostasDadas.size == totalPerguntas) {

                val totalSim = respostasDadas.values.count { it }


                val nota = (totalSim.toFloat() / totalPerguntas.toFloat() * 100).toInt()


                val tarefasPendentes = totalPerguntas - totalSim


                val entidade = DiagnosticoEntity(
                    notaCompliance = nota,
                    tarefasPendentes = tarefasPendentes
                )
                repository.salvarDiagnostico(entidade)

               // volta para o Dashboard yay
                withContext(Dispatchers.Main) {
                    onFinalizado()
                }
            }
        }
    }
}