package com.hig.lgpd_facil.model

import androidx.annotation.StringRes


data class TarefaPlano(
    val id: Int,
    @StringRes val descricao: Int,
    var completada: Boolean = false
)
