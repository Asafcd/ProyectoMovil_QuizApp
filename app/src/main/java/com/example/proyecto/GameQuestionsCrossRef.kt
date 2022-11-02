package com.example.proyecto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (primaryKeys = ["gameId, questionId"], tableName = "game_questions")
data class GameQuestionsCrossRef(
    val gameId: Int,
    val questionId: Int
)
