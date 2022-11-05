package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.room.Room

class ScoresActivity : AppCompatActivity() {
    lateinit var llScores: LinearLayout
    lateinit var txtTop: TextView
    lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        val db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()
        val gameScores = db.gameDao().getAllTopGamesWithScore()

        txtTop = findViewById<TextView>(R.id.txt_top)
        txtTop.text = "Puntaje - Iniciales"

        llScores = findViewById<LinearLayout>(R.id.linear_scores)
        for(game in gameScores){
            val tvGame = TextView(this)
            var score = game.score

            tvGame.text = "   ${score}   -   FRN"
            tvGame.gravity = txtTop.gravity
            tvGame.textSize = 25f
            llScores.addView(tvGame)
        }

        btnBack = findViewById<Button>(R.id.btn_regresar)
        btnBack.setOnClickListener{
            val act1 = Intent(this,MainMenuActivity::class.java)
            startActivity(act1)
        }

    }


}