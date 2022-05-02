package com.example.catknowledgebase.api

import com.example.catknowledgebase.model.FactItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FactApi {
    companion object{
        const val BASE_URL = "https://cat-fact.herokuapp.com/"

    }

    @GET("facts/random")
    suspend fun getCatFacts(
    ): Response<FactItem>
}



