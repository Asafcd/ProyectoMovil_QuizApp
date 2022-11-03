package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.room.ColumnInfo
import androidx.room.Room
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
    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.option_menu)

        var db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()
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
        dificulty = findViewById(R.id.spinner)
        hintsActive = findViewById(R.id.Switch)
        todos = findViewById(R.id.CbTemaTodos)
        geografia = findViewById(R.id.CbTema1)
        cultGen = findViewById(R.id.CbTema2)
        animales = findViewById(R.id.CbTema3)
        videojuegos = findViewById(R.id.CbTema4)
        peliculas = findViewById(R.id.CbTema5)
        numHints = findViewById(R.id.sliderPistas)
        numQuestions = findViewById(R.id.slider)


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
            act3.putExtra(QUESTIONS, numQuestions.value.toInt())
            val topics = listOf<Boolean>()
            val extras = Bundle()
            var strings = mutableListOf<String>()
            if(todos.isChecked){
                extras.putStringArray("topics", arrayOf<String>("Geografía","Cultura General","Animales","Videojuegos","Películas"))
            }
            else{
                if(geografia.isChecked){
                    strings.add("Geografia")
                }
                if(cultGen.isChecked){
                    strings.add("Cultura General")
                }
                if(animales.isChecked){
                    strings.add("Animales")
                }
                if(videojuegos.isChecked){
                    strings.add("Videojuegos")
                }
                if(peliculas.isChecked){
                    strings.add("Películas")
                }

                var arr = strings.toTypedArray()

                extras.putStringArray("topics", arr)
            }

//            val game = Game(0,0.0,false,hintsActive.isChecked, dificulty.selectedItemPosition+1, numHints.value.toInt(),"FRN",true)
            db.gameDao().AddGame(0.0, false,hintsActive.isChecked, dificulty.selectedItemPosition+1, numHints.value.toInt(),"FRN",true)

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

//val score: Double,
//val finished: Boolean,
//@ColumnInfo(name = "hints_enabled") val hintsEnabled: Boolean,
//val difficulty: Int,
//@ColumnInfo(name = "hints_available") val hintsAvailable: Int,
//val player: String,
//val isStarted: Boolean)