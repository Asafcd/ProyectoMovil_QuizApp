package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class ScoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        val db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()
        val topScores = db.gameDao().getGamesWithScore()


    }


}