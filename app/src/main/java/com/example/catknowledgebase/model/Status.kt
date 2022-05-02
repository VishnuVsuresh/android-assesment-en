package com.example.catknowledgebase.model

import androidx.room.Entity


@Entity(tableName = "Status")
data class Status(
    val sentCount: Int,
    val verified: Boolean
)