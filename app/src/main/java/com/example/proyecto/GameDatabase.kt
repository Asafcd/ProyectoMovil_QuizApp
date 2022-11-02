package com.example.proyecto

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [Question::class, Answer::class, Game::class, GameQuestionsCrossRef::class], version = 1)
abstract class GameDatabase: RoomDatabase() {

    abstract fun gameDao(): GameDao
}