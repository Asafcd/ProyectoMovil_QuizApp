package com.example.proyecto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (primaryKeys = [""])
data class GameQuestionsCrossRef(
    val gameId: Int,
    val questionId: Int
)
