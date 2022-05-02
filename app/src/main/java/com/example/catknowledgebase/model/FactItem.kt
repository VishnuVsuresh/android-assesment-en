package com.example.catknowledgebase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
data class FactItem(
    val __v: Int,
    @PrimaryKey
    val _id: String,
    val createdAt: String,
    val deleted: Boolean,
    val source: String?,
    val text: String,
    val type: String,
    val updatedAt: String,
    val used: Boolean,
    val user: String,
    @ColumnInfo(name = "catUrl", defaultValue = "https://cataas.com/cat/" )
    var catUrl: String?
)

