package com.hig.lgpd_facil.data.remote

import com.hig.lgpd_facil.model.RespostaCamara
import retrofit2.http.GET
import retrofit2.http.Query

interface CamaraApi {

    @GET("proposicoes")
    suspend fun buscarLeis(
        @Query("siglaTipo") siglaTipo: String = "PL",
        @Query("itens") itens: Int = 15,
        @Query("ordem") ordem: String = "DESC",
        @Query("ordenarPor") ordenarPor: String = "ano",

        @Query("keywords") palavrasChave: String = "LGPD, proteção de dados"
    ): RespostaCamara
}