package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

val DIFICULTY = "DIFICULTY"
val SCORE = "SCORE"
val CORRECTAS = "CORRECTAS"
val HINTS = "HINTS"
val TOPICS = "TOPICS"
val QUESTIONS = "QUESTIONS"

class OptionsMenuActivity : AppCompatActivity() {

    private lateinit var btn_regresar: Button
    private lateinit var btn_iniciar: Button
    private lateinit var dificulty: Spinner
    private lateinit var todos: CheckBox
    private lateinit var geografia: CheckBox
    private lateinit var cultGen: CheckBox
    private lateinit var animales: CheckBox
    private lateinit var videojuegos: CheckBox
    private lateinit var peliculas: CheckBox
    private lateinit var numQuestions: Slider
    private lateinit var hintsActive: Switch
    private lateinit var numHints: Slider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.option_menu)
        dificulty = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.Dificultad,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            dificulty.adapter = adapter
        }
        hintsActive = findViewById(R.id.simpleSwitch)
        btn_iniciar = findViewById<Button>(R.id.btn_iniciar)
        btn_iniciar.setOnClickListener {
            val act3 = Intent(this,GameActivity::class.java)
            if(hintsActive.isChecked){
                act3.putExtra(HINTS, numHints.value)
            }
            else {
                act3.putExtra(HINTS, 0)
            }
            act3.putExtra(DIFICULTY, dificulty.selectedItemPosition+1)
            act3.putExtra(QUESTIONS, numQuestions.value)
            val topics = listOf<Boolean>()
            val extras = Bundle()
            if(todos.isChecked){
                extras.putBooleanArray("topics", booleanArrayOf(true,true,true,true,true))
            }
            else{
                extras.putBooleanArray("topics", booleanArrayOf(geografia.isChecked,cultGen.isChecked,animales.isChecked,videojuegos.isChecked,peliculas.isChecked))
            }
            act3.putExtra(TOPICS, extras)
            startActivity(act3)
        }
        btn_regresar = findViewById<Button>(R.id.btn_regresar)
        btn_regresar.setOnClickListener {
            val act1 = Intent(this,MainMenuActivity::class.java)
            startActivity(act1)
        }
    }
}