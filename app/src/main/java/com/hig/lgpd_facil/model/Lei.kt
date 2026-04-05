package com.hig.lgpd_facil.model

import com.google.gson.annotations.SerializedName


data class RespostaCamara(
    @SerializedName("dados") val dados: List<Lei>
)

data class Lei(
    val id: Long,
    val siglaTipo: String,
    val numero: Int,
    val ano: Int,
    val ementa: String
)