package com.example.catknowledgebase.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catknowledgebase.model.FactItem


@Dao
interface FactDao {
    @Insert
    suspend fun addToFact(fact: FactItem)

    @Query("SELECT * FROM facts")
    suspend fun getFacts(): List<FactItem>


    @Query("DELETE FROM facts WHERE facts._id = :id" )
    suspend fun removeFact(id: String) : Int

    @Query("SELECT COUNT(*) FROM facts")
    fun getCount(): Int
}