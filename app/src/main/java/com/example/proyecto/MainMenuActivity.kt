package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isGone
import androidx.room.Room


class MainMenuActivity : AppCompatActivity() {

//    private lateinit var spin: Spinner
    private lateinit var btn_puntuaciones: Button
    private lateinit var btn_jugar: Button
    private lateinit var btn_continuar: Button
   // var gameIsFinished: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        var db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()
        var gameIsFinished = true
        if(db.gameDao().Size() > 0){
            gameIsFinished = db.gameDao().isFinished()
        }
        btn_jugar = findViewById<Button>(R.id.btn_Jugar)
        btn_jugar.setOnClickListener {
            val act2 = Intent(this,OptionsMenuActivity::class.java)
            startActivity(act2)
        }
        btn_continuar = findViewById<Button>(R.id.btn_Continuar)
        btn_continuar.setOnClickListener {
            val act3 = Intent(this,GameActivity::class.java)
            startActivity(act3)
        }
        if(gameIsFinished){
            btn_continuar.isGone = true
        }else{
            btn_continuar.isGone = false
        }

        btn_puntuaciones = findViewById<Button>(R.id.btn_Puntuaciones)
        btn_puntuaciones.setOnClickListener {
            val act4 = Intent(this,ScoresActivity::class.java)
            startActivity(act4)
        }

    }
}