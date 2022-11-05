package com.example.proyecto

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val scoreModel: ScoreModel by viewModels()
        val db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()
        val topScores = db.gameDao().getTopGamesWithScore()
        scoreModel.score = topScores[0].score

        txt_points = findViewById(R.id.txt_puntuacion)
        txt_points.text = "Puntaje: ${scoreModel.score }"

        txt_top1 = findViewById(R.id.txt_top1)
        txt_top2 = findViewById(R.id.txt_top2)
        txt_top3 = findViewById(R.id.txt_top3)
        txt_top4 = findViewById(R.id.txt_top4)
        txt_top5 = findViewById(R.id.txt_top5)
        ll_scores = findViewById(R.id.linear_scores)

        var topList = listOf<TextView>(txt_top1,txt_top2,txt_top3,txt_top4,txt_top5)

        for(top in topScores){
            for(txt in topList){
                var topn = 0
                scoreModel.player = top.player
                scoreModel.score = top.score
                txt.text = "${topn++} - ${scoreModel.player} - ${scoreModel.score}"
            }
        }

    }
}