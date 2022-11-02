package com.example.proyecto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GameWithQuestions(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "questionId",
        associateBy = Junction(GameQuestionsCrossRef::class)
    )
    val questions: List<Question>
    )