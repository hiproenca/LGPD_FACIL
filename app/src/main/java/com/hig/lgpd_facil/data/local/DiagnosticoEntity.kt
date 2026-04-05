package com.hig.lgpd_facil.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empresa_diagnostico")
data class DiagnosticoEntity(
    @PrimaryKey val id: Int = 1,
    val notaCompliance: Int,
    val tarefasPendentes: Int
)
