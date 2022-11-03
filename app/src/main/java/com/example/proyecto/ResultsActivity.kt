package com.example.proyecto

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.IOException
import java.net.URL

class ResultsActivity : AppCompatActivity() {
    lateinit var txtPuntuacion: TextView
    lateinit var txtCorrectas: TextView
    lateinit var txtDificultad: TextView
    lateinit var txtHintsUsadas: TextView
    lateinit var txtAdvice: TextView
    lateinit var imgResult: ImageView
    var score = 0
    var corrects = 0
    var hintsUsed = 0
    var dificulty = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()
        val topScores = db.gameDao().getGamesWithScore()
        txtPuntuacion = findViewById(R.id.txt_puntuacion)
        txtCorrectas = findViewById(R.id.txt_correctas)
        txtDificultad = findViewById(R.id.txt_dificultadUsada)
        txtHintsUsadas = findViewById(R.id.txt_pistasUsadas)
        imgResult = findViewById(R.id.img_result)
        txtAdvice = findViewById(R.id.txt_advice)


        val extras = intent.extras

        if (extras != null) {
            score = extras.getInt(SCORE)
            corrects = extras.getInt(CORRECTAS)
            hintsUsed = extras.getInt(HINTS)
            dificulty = extras.getString(DIFICULTY).toString()
        }

        txtPuntuacion.text = "Puntaje: ${score}"
        txtDificultad.text = "Dificultad: ${dificulty}"
        txtCorrectas.text = "Correctas: ${corrects}"
        txtHintsUsadas.text = "Pistas Usadas: ${hintsUsed}"

        if(score>=801) {imgResult.setImageResource(R.drawable.perfecto); txtAdvice.text = "EXCELENTE!"}
        if(score in 601..800) {imgResult.setImageResource(R.drawable.meh); txtAdvice.text = "Puedes hacerlo mejor, chavo"}
        if(score in 401..600) {imgResult.setImageResource(R.drawable.mal); txtAdvice.text = "Muy conformista de tu parte"}
        if(score in 201..400) {imgResult.setImageResource(R.drawable.muymal); txtAdvice.text = "Mi hijo de 9 sabe mas que tú"}
        if(score<200) {imgResult.setImageResource(R.drawable.terrible); txtAdvice.text = "Regresa al kinder mejor"}






    }

}