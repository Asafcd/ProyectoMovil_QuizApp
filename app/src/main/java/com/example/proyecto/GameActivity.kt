package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels

class GameActivity : AppCompatActivity() {

    private lateinit var txtQuestion: TextView
    private lateinit var btnAnswer1: Button
    private lateinit var btnAnswer2: Button
    private lateinit var btnAnswer3: Button
    private lateinit var btnAnswer4: Button
    private lateinit var btnHint: Button
    private lateinit var txtScore: TextView
    private lateinit var txtQuestionNumber: TextView
    private lateinit var txtRemainigHints: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)

        val gameModel: GameModel // by viewModels() ahhhhhhhhh marca error :(

        txtQuestion= findViewById(R.id.txt_pregunta)
        btnAnswer1= findViewById(R.id.btn_respuesta1)
        btnAnswer2= findViewById(R.id.btn_respuesta2)
        btnAnswer3= findViewById(R.id.btn_respuesta3)
        btnAnswer4= findViewById(R.id.btn_respuesta4)
        btnHint= findViewById(R.id.btn_hint)
        txtQuestionNumber = findViewById(R.id.txt_pregunta)
        txtScore= findViewById(R.id.txt_puntuacion)
        txtRemainigHints = findViewById(R.id.txt_pistasRestantes)






    }
}