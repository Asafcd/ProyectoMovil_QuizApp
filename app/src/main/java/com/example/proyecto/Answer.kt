package com.example.proyecto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "answers")
data class Answer(
    @PrimaryKey (autoGenerate = true)
    val answerId: Int,
    val questionId: Int,
    val content: String,
    val correct: Boolean
)
