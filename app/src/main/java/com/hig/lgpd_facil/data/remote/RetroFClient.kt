package com.hig.lgpd_facil.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dadosabertos.camara.leg.br/api/v2/"

    val api: CamaraApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CamaraApi::class.java)
    }
}