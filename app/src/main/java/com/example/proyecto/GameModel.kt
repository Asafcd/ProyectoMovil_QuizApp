package com.example.proyecto

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameModel : ViewModel() {
    private val allQuestions = mutableListOf<Question>()
    private var gameQuestions = mutableListOf<Question>()
    private var NumberOfQuestions = 0
    private var currentQuestionIndex = 0
    private var questionAnswers = mutableListOf<List<String>>()
    var gameDifficulty = 0
    var score = 0
    private var questionsAnswered = 0

    init {
        allQuestions.add(
            Question(
                "¿Cuál es la extensión territorial de la CDMX?",
                "1,485 km2",
                listOf("1,845 km2", "2,172 km2", "937 km2"),
                "Geografía",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Cuál es el estado más pequeño de México?",
                "CDMX",
                listOf("Colima", "Tlaxcala", "Morelos"),
                "Geografía",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿A qué estado le pertenece la ciudad de Tijuana?",
                "Baja California",
                listOf("Baja California Sur", "California", "Sonora"),
                "Geografía",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Cuál es el rio más grande del mundo?",
                "Amazonas",
                listOf("Nilo", "Mississippi", "Congo"),
                "Geografía",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Cuántos oceanos hay en el mundo?",
                "5",
                listOf("7", "2", "4"),
                "Geografía",
                false,
                ""
            )
        )

        allQuestions.add(
            Question(
                "¿Quién es el autor de la frase 'Pienso luego existo'?",
                "Descartes",
                listOf("Locke", "Socrates", "Platón"),
                "Cultura General",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Cuál no es una maravilla del mundo moderno?",
                "La tumba de Tutankamón",
                listOf("La muralla china", "Chichen Itzá", "Las pirámides de Giza"),
                "Cultura General",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿En qué año se descubrió América?",
                "1492",
                listOf("1576", "1824", "1342"),
                "Cultura General",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Cuántos litros de sangre tiene una persona adulta?",
                "4 - 6 L",
                listOf("2 - 4 L", "7+ L", "5 - 7 L"),
                "Cultura General",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿¿Qué continente no es parte del viejo mundo?\n?",
                "América",
                listOf("África", "Asia", "Europa"),
                "Cultura General",
                false,
                ""
            )
        )

        allQuestions.add(
            Question(
                "¿Cuántos reinos animales existen?",
                "5",
                listOf("9", "12", "17"),
                "Animales",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Qué tipo de animal es el tiburón?",
                "Pez",
                listOf("Mamífero", "Anfibio", "Reptil"),
                "Animales",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "Los animales que tienen el cuerpo cubierto por una escama dura y áspera se llaman...",
                "Reptiles",
                listOf("Mamíferos", "Anfibios", "Peces"),
                "Animales",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "Las esponjas son animales invertebrados de la familia de los...",
                "Poríferos",
                listOf("Insectos", "Gusanos", "Moluscos"),
                "Animales",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "Los animales invertebrados, que tienen 4 pares de patas y no poseen antenas, se llaman...",
                "Arácnidos",
                listOf("Insectos", "Crustaceos", "Poríferos"),
                "Animales",
                false,
                ""
            )
        )

        allQuestions.add(
            Question(
                "¿Quién es el protagonista de 'The Legend of Zelda'?",
                "Link",
                listOf("Zelda", "Ganondorf", "Impa"),
                "Videojuegos",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Cuál es el juego más vendido de la historia?",
                "Wii Sports",
                listOf("Tetris", "Super Mario", "Pokemon Red/Blue"),
                "Videojuegos",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Quién es el protagonista de 'The Legend of Zelda'?",
                "Link",
                listOf("Zelda", "Ganondorf", "Impa"),
                "Videojuegos",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿En cuál fue el primer juego en donde apareció el personaje 'Mario'?",
                "Donkey Kong",
                listOf("Super Mario Bros", "Super Smash Brothers 64", "The Legend of Zelda"),
                "Videojuegos",
                false,
                ""
            )
        )
        allQuestions.add(
            Question(
                "¿Cuál juego de los siguientes salió primero?",
                "Pong",
                listOf("Tetris", "Pac-man", "Sonic"),
                "Videojuegos",
                false,
                ""
            )
        )

    }

    fun GetRandomQuestions(difficulty: Int) {

        for (i in 0 ..9){
            //val randInt = Random.nextInt(allQuestions.size)
            //var question = allQuestions[randInt]
            //gameQuestions.add(question)
            var question = allQuestions.random()
            while (gameQuestions.indexOf(question) != -1){
                question = allQuestions.random()
            }
            gameQuestions.add(question)

            var answers = mutableListOf<String>()
            answers.add(question.answer)
            var wrongAnswer = question.wrong_answers.random()
            answers.add(wrongAnswer)
            for (i in 2..difficulty) {
                while (answers.indexOf(wrongAnswer) != -1) {
                    wrongAnswer = question.wrong_answers.random()
                }
                answers.add(wrongAnswer)
            }
            answers.shuffle()
            questionAnswers.add(answers)
        }
        NumberOfQuestions = gameQuestions.size
    }

    fun GetQuestionAnswers(): List<String> {
        return questionAnswers[currentQuestionIndex]
    }

    val TotalNumberOfQuestions: Int
        get() = NumberOfQuestions

    val currentQuestion: Question
        get() = gameQuestions[currentQuestionIndex]

    val currentQuestionText: String
        get() = gameQuestions[currentQuestionIndex].question

    val currentQuestionAnswer: String
        get() = gameQuestions[currentQuestionIndex].answer

    val questionNumber: Int
        get() = currentQuestionIndex +1

    val isEmpty: Boolean
         get() = gameQuestions.size == 0


    fun nextQuestion(){
        currentQuestionIndex = (currentQuestionIndex + 1) % gameQuestions.size
    }

    fun prevQuestion() {
        if (currentQuestionIndex >= 0) {
            currentQuestionIndex--
        }
        if (currentQuestionIndex == -1) {
            currentQuestionIndex = gameQuestions.size - 1
        }
    }

    fun Answer(){
        questionsAnswered++
    }

    val IsFinished: Boolean
        get() = questionsAnswered == gameQuestions.size


}