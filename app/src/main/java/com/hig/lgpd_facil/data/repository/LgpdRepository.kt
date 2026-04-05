package com.hig.lgpd_facil.data.repository

import com.hig.lgpd_facil.data.local.DiagnosticoDao
import com.hig.lgpd_facil.data.local.DiagnosticoEntity
import com.hig.lgpd_facil.data.remote.RetrofitClient
import com.hig.lgpd_facil.model.Lei

class LgpdRepository(private val diagnosticoDao: DiagnosticoDao) {

    // ==========================================
    // PARTE 1: BANCO DE DADOS LOCAL (OFFLINE)
    // ==========================================

    suspend fun obterDiagnostico(): DiagnosticoEntity? {
        return diagnosticoDao.getDiagnostico()
    }

    suspend fun salvarDiagnostico(diagnostico: DiagnosticoEntity) {
        diagnosticoDao.salvarDiagnostico(diagnostico)
    }

    // ==========================================
    // PARTE 2: API DA CÂMARA DOS DEPUTADOS
    // ==========================================

    suspend fun buscarLeis(): List<Lei> {
        return try {
            // Faz a chamada na internet usando o nosso Singleton
            val resposta = RetrofitClient.api.buscarLeis()
            // Retorna apenas a lista de leis que está dentro do JSON
            resposta.dados
        } catch (e: Exception) {
            // Se o usuário estiver sem internet ou a API cair,
            // não deixamos o app "crashar" (fechar sozinho).
            // Retornamos apenas uma lista vazia.
            emptyList()
        }
    }
}