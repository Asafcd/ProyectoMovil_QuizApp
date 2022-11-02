package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
val DIFICULTY = "DIFICULTY"
val PISTAS = "CLUES"
val TEMAS = "TOPICS"
val PREGUNTAS = "QUESTIONS"
class OptionsMenuActivity : AppCompatActivity() {

    private lateinit var btn_regresar: Button
    private lateinit var btn_iniciar: Button
    private lateinit var spin: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.option_menu)
        spin = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.Dificultad,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spin.adapter = adapter
        }
        btn_iniciar = findViewById<Button>(R.id.btn_iniciar)
        btn_iniciar.setOnClickListener {
            val act = Intent(this,GameActivity::class.java)
            startActivity(act)
        }
        btn_regresar = findViewById<Button>(R.id.btn_regresar)
        btn_regresar.setOnClickListener {
            val act = Intent(this,MainMenuActivity::class.java)
            startActivity(act)
        }
    }
}