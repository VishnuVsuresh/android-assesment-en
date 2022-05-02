package com.example.catknowledgebase.data.local

import com.example.catknowledgebase.api.FactApi
import com.example.catknowledgebase.model.FactItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val factDao: FactDao,private val factApi: FactApi
):FactRepository {

     override suspend fun addFact(factItem: FactItem)=factDao.addToFact(factItem)
    override suspend  fun getFacts()=factDao.getFacts()

    override suspend  fun removeFact(id:String){
        factDao.removeFact(id)
    }
    override suspend  fun getCatFact(): Flow <Response<FactItem>> = flow{
        emit(factApi.getCatFacts())
    }
}