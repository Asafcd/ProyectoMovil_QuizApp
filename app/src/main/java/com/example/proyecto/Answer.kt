package com.example.proyecto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "Answer")
data class Answer(
    @PrimaryKey val id: Int,
    val content: String,
    val correct: Boolean
)
