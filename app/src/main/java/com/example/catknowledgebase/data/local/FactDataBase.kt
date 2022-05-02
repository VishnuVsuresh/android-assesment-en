package com.example.catknowledgebase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catknowledgebase.model.FactItem

@Database(
    entities = [FactItem::class],
    version = 1
)
abstract class FactDataBase : RoomDatabase(){
    abstract fun getFactDao(): FactDao
}