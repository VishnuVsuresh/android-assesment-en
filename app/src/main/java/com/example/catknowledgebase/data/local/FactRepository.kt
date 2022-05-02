package com.example.catknowledgebase.data.local

import com.example.catknowledgebase.api.FactApi
import com.example.catknowledgebase.model.FactItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface FactRepository{
    suspend fun addFact(factItem: FactItem)
    suspend fun getFacts():List<FactItem>
    suspend fun removeFact(id:String)
    suspend fun getCatFact(): Flow<Response<FactItem>>

}