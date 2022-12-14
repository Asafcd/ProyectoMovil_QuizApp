package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.IOException
import java.net.URL

class ResultsActivity : AppCompatActivity() {
    lateinit var txt_points: TextView
    lateinit var txt_top1: TextView
    lateinit var txt_top2: TextView
    lateinit var txt_top3: TextView
    lateinit var txt_top4: TextView
    lateinit var txt_top5: TextView
    lateinit var ll_scores: LinearLayout
    lateinit var btnInicio: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val scoreModel: ScoreModel by viewModels()
        val db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()
        val topScores = db.gameDao().getTopGamesWithScore()
        val currentScore = db.gameDao().getGamesWithScore()[0].score
        scoreModel.score = currentScore

        txt_points = findViewById(R.id.txt_puntuacion)
        txt_points.text = "Puntaje: ${scoreModel.score }"

        txt_top1 = findViewById(R.id.txt_top1)
        txt_top2 = findViewById(R.id.txt_top2)
        txt_top3 = findViewById(R.id.txt_top3)
        txt_top4 = findViewById(R.id.txt_top4)
        txt_top5 = findViewById(R.id.txt_top5)
        ll_scores = findViewById(R.id.linear_scores)

        var topList = listOf<TextView>(txt_top1,txt_top2,txt_top3,txt_top4,txt_top5)

        for(index in 0..4){
            topList[index].text = "${index+1}   -    ${topScores[index].score}   -   FRN"
        }

        btnInicio = findViewById(R.id.btn_regresar)
        btnInicio.setOnClickListener {
            val act1 = Intent(this,MainMenuActivity::class.java)
            startActivity(act1)
        }
    }
}