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

        var allQuestions = listOf<QuestionWithAnswers>(
            QuestionWithAnswers(
                Question(
                    1,
                    "¿Cuál es la extensión territorial de la CDMX?",
                    "Geografía",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        1,
                        1,
                        "1,485 km2",
                        true
                    ),
                    Answer(
                        2,
                        1,
                        "1,845 km2",
                        false
                    ),
                    Answer(
                        3,
                        1,
                        "2,172 km2",
                        false
                    ),
                    Answer(
                        4,
                        1,
                        "937 km2",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    2,
                    "¿Cuál es el estado más pequeño de México?",
                    "Geografía",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        5,
                        2,
                        "CDMX",
                        true
                    ),
                    Answer(
                        6,
                        2,
                        "Colima",
                        false
                    ),
                    Answer(
                        7,
                        2,
                        "Tlaxcala",
                        false
                    ),
                    Answer(
                        8,
                        2,
                        "Morelos",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    3,
                    "¿A qué estado le pertenece la ciudad de Tijuana?",
                    "Geografía",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        9,
                        3,
                        "Baja California",
                        true
                    ),
                    Answer(
                        10,
                        3,
                        "Baja California Sur",
                        false
                    ),
                    Answer(
                        11,
                        3,
                        "California",
                        false
                    ),
                    Answer(
                        12,
                        3,
                        "Sonora",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    4,
                    "¿Cuál es el rio más grande del mundo?",
                    "Geografía",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        13,
                        4,
                        "Amazonas",
                        true
                    ),
                    Answer(
                        14,
                        4,
                        "Nilo",
                        false
                    ),
                    Answer(
                        15,
                        4,
                        "Congo",
                        false
                    ),
                    Answer(
                        16,
                        4,
                        "Mississippi",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    5,
                    "¿Cuántos oceanos hay en el mundo?",
                    "Geografía",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        17,
                        5,
                        "5",
                        true
                    ),
                    Answer(
                        18,
                        5,
                        "7",
                        false
                    ),
                    Answer(
                        19,
                        5,
                        "2",
                        false
                    ),
                    Answer(
                        20,
                        5,
                        "4",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    6,
                    "¿Quién es el autor de la frase \"Pienso luego existo\"?",
                    "Cultura General",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        21,
                        6,
                        "Descartes",
                        true
                    ),
                    Answer(
                        22,
                        6,
                        "Locke",
                        false
                    ),
                    Answer(
                        23,
                        6,
                        "Socrates",
                        false
                    ),
                    Answer(
                        24,
                        6,
                        "Platón",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    7,
                    "¿Cuál no es una maravilla del mundo moderno?",
                    "Cultura General",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        25,
                        7,
                        "La tumba de Tutankamón",
                        true
                    ),
                    Answer(
                        26,
                        7,
                        "La muralla china",
                        false
                    ),
                    Answer(
                        27,
                        7,
                        "Chichen Itzá",
                        false
                    ),
                    Answer(
                        28,
                        7,
                        "Las pirámides de Giza",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    8,
                    "¿En qué año se descubrió América?",
                    "Cultura General",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        29,
                        8,
                        "1492",
                        true
                    ),
                    Answer(
                        30,
                        8,
                        "1576",
                        false
                    ),
                    Answer(
                        31,
                        8,
                        "1824",
                        false
                    ),
                    Answer(
                        32,
                        8,
                        "1342",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    9,
                    "¿Cuántos litros de sangre tiene una persona adulta?",
                    "Cultura General",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        33,
                        9,
                        "4 - 6 L",
                        true
                    ),
                    Answer(
                        34,
                        9,
                        "2 - 4 L",
                        false
                    ),
                    Answer(
                        35,
                        9,
                        "7+ L",
                        false
                    ),
                    Answer(
                        36,
                        9,
                        "5 - 7 L",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    10,
                    "¿Qué continente no es parte del viejo mundo?",
                    "Cultura General",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        37,
                        10,
                        "América",
                        true
                    ),
                    Answer(
                        38,
                        10,
                        "África",
                        false
                    ),
                    Answer(
                        39,
                        10,
                        "Europa",
                        false
                    ),
                    Answer(
                        40,
                        10,
                        "Asia",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    11,
                    "¿Cuántos reinos animales existen?",
                    "Animales",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        41,
                        11,
                        "5",
                        true
                    ),
                    Answer(
                        42,
                        11,
                        "9",
                        false
                    ),
                    Answer(
                        43,
                        11,
                        "12",
                        false
                    ),
                    Answer(
                        44,
                        11,
                        "17",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    12,
                    "¿Qué tipo de animal es el tiburón?",
                    "Animales",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        45,
                        12,
                        "Pez",
                        true
                    ),
                    Answer(
                        46,
                        12,
                        "Mamífero",
                        false
                    ),
                    Answer(
                        47,
                        12,
                        "Anfibio",
                        false
                    ),
                    Answer(
                        48,
                        12,
                        "Reptil",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    13,
                    "Los animales que tienen el cuerpo cubierto por una escama dura y áspera se llaman...",
                    "Animales",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        49,
                        13,
                        "Reptiles",
                        true
                    ),
                    Answer(
                        50,
                        13,
                        "Mamíferos",
                        false
                    ),
                    Answer(
                        51,
                        13,
                        "Mamíferos",
                        false
                    ),
                    Answer(
                        52,
                        13,
                        "Peces",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    14,
                    "Las esponjas son animales invertebrados de la familia de los...",
                    "Animales",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        53,
                        14,
                        "Poríferos",
                        true
                    ),
                    Answer(
                        54,
                        14,
                        "Insectos",
                        false
                    ),
                    Answer(
                        55,
                        14,
                        "Gusanos",
                        false
                    ),
                    Answer(
                        56,
                        14,
                        "Moluscos",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    15,
                    "Los animales invertebrados, que tienen 4 pares de patas y no poseen antenas, se llaman...",
                    "Animales",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        57,
                        15,
                        "Arácnidos",
                        true
                    ),
                    Answer(
                        58,
                        15,
                        "Insectos",
                        false
                    ),
                    Answer(
                        59,
                        15,
                        "Crustaceos",
                        false
                    ),
                    Answer(
                        60,
                        15,
                        "Poríferos",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    16,
                    "¿Quién es el protagonista de \"The Legend of Zelda\"",
                    "Videojuegos",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        61,
                        16,
                        "Link",
                        true
                    ),
                    Answer(
                        62,
                        16,
                        "Zelda",
                        false
                    ),
                    Answer(
                        63,
                        16,
                        "Impa",
                        false
                    ),
                    Answer(
                        64,
                        16,
                        "Ganondorf",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    17,
                    "¿Cuál es el juego más vendido de la historia?",
                    "Videojuegos",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        65,
                        17,
                        "Wii Sports",
                        true
                    ),
                    Answer(
                        66,
                        17,
                        "Tetris",
                        false
                    ),
                    Answer(
                        67,
                        17,
                        "Super Mario",
                        false
                    ),
                    Answer(
                        68,
                        17,
                        "Pokemon Red/Blue",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    18,
                    "¿En cuál fue el primer juego en donde apareció el personaje \"Mario\"?",
                    "Videojuegos",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        69,
                        18,
                        "Donkey Kong",
                        true
                    ),
                    Answer(
                        70,
                        18,
                        "Super Mario Bros",
                        false
                    ),
                    Answer(
                        71,
                        18,
                        "Super Smash Bros 64",
                        false
                    ),
                    Answer(
                        72,
                        18,
                        "The Legend of Zelda",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    19,
                    "¿Cuántos luchadores hay en Street Fighter II?",
                    "Videojuegos",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        73,
                        19,
                        "12",
                        true
                    ),
                    Answer(
                        74,
                        19,
                        "8",
                        false
                    ),
                    Answer(
                        75,
                        19,
                        "10",
                        false
                    ),
                    Answer(
                        76,
                        19,
                        "16",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    20,
                    "¿Cuál juego de los siguientes salió primero?",
                    "Videojuegos",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        77,
                        20,
                        "Pong",
                        true
                    ),
                    Answer(
                        78,
                        20,
                        "Mario bros",
                        false
                    ),
                    Answer(
                        79,
                        20,
                        "Pac Man",
                        false
                    ),
                    Answer(
                        80,
                        20,
                        "Sonic",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    21,
                    "Cuál película le dió el \"Oscar\" a Leonardo Dicaprio",
                    "Películas",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        81,
                        21,
                        "Revenant",
                        true
                    ),
                    Answer(
                        82,
                        21,
                        "El gran Gatbsy",
                        false
                    ),
                    Answer(
                        83,
                        21,
                        "Wall Street",
                        false
                    ),
                    Answer(
                        84,
                        21,
                        "Django",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    22,
                    "¿Quién dirigió titanes del pacífico?",
                    "Películas",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        85,
                        22,
                        "Guillermo del Toro",
                        true
                    ),
                    Answer(
                        86,
                        22,
                        "Alfonso Quaron",
                        false
                    ),
                    Answer(
                        87,
                        22,
                        "Tarantino",
                        false
                    ),
                    Answer(
                        88,
                        22,
                        "Waititi",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    23,
                    "Película en la que aparecen 3 Spiderman",
                    "Películas",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        89,
                        23,
                        "Amazing Spiderman",
                        true
                    ),
                    Answer(
                        90,
                        23,
                        "Spiderman: Far From Home",
                        false
                    ),
                    Answer(
                        91,
                        23,
                        "Spiderman: Homecoming",
                        false
                    ),
                    Answer(
                        92,
                        23,
                        "Spiderman: No Way Home",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    24,
                    "¿A que casa pertenece Harry Potter?",
                    "Películas",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        93,
                        24,
                        "Griffindor",
                        true
                    ),
                    Answer(
                        94,
                        24,
                        "Slytherin",
                        false
                    ),
                    Answer(
                        95,
                        24,
                        "Hufflepuff",
                        false
                    ),
                    Answer(
                        96,
                        24,
                        "Ravenclaw",
                        false
                    ),
                )
            ),
            QuestionWithAnswers(
                Question(
                    25,
                    "¿A que distrito pertenece Katniss?",
                    "Películas",
                    false,
                    "",
                    false
                ),
                listOf<Answer>(
                    Answer(
                        97,
                        25,
                        "8",
                        true
                    ),
                    Answer(
                        98,
                        25,
                        "10",
                        false
                    ),
                    Answer(
                        99,
                        25,
                        "11",
                        false
                    ),
                    Answer(
                        100,
                        25,
                        "12",
                        false
                    ),
                )
            ))


        btn_iniciar = findViewById<Button>(R.id.btn_iniciar)
        btn_iniciar.setOnClickListener {
//            for(questionWithAnswer in allQuestions){
//                db.gameDao().UpdateQuestion(questionWithAnswer.question)
//            }

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

                //var arr = strings.toTypedArray()

                //extras.putStringArray("topics", arr)


            }

//            val game = Game(0,0.0,false,hintsActive.isChecked, dificulty.selectedItemPosition+1, numHints.value.toInt(),"FRN",true)
            db.gameDao().AddGame(0.0, false,hintsActive.isChecked, dificulty.selectedItemPosition+1, numHints.value.toInt(),"FRN",true)
            val gameId = db.gameDao().getLastUnfinishedGameId()
            val questionsFiltered = db.gameDao().GetQuestionsByTopics(strings)
            questionsFiltered.shuffled()
            //var questionsLimited = mutableListOf<QuestionWithAnswers>()
            for(i in 0..numQuestions.value.toInt()-1){
                //questionsLimited.add(questionsFiltered[i])
                db.gameDao().addGameQuestions(gameId, questionsFiltered[i].question.questionId)
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

//val score: Double,
//val finished: Boolean,
//@ColumnInfo(name = "hints_enabled") val hintsEnabled: Boolean,
//val difficulty: Int,
//@ColumnInfo(name = "hints_available") val hintsAvailable: Int,
//val player: String,
//val isStarted: Boolean)