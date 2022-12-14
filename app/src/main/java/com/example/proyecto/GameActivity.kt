package com.example.proyecto

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.room.Room


class GameActivity : AppCompatActivity() {

    private lateinit var txtQuestion: TextView
    private lateinit var btnAnswer1: Button
    private lateinit var btnAnswer2: Button
    private lateinit var btnAnswer3: Button
    private lateinit var btnAnswer4: Button
    private lateinit var btnHint: Button
    private lateinit var txtSubject: TextView
    private lateinit var txtQuestionNumber: TextView
    private lateinit var txtRemainingHints: TextView
    private lateinit var btnPrev: Button
    private lateinit var btnNext: Button
    private lateinit var btnFinish: Button


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            val alertDialog = AlertDialog.Builder(this@GameActivity)
            alertDialog.setMessage("¿Quieres salir de la partida?")
            alertDialog.setTitle("Confirmar salida")
            alertDialog.setPositiveButton("Si")  { dialog, id ->

                super.onBackPressed()
                var db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()


                var gameData = db.gameDao().getLastUnfinishedGame()

                val gameModel: GameModel by viewModels()

                var gameId = gameData.gameId
                var gameDif = gameData.difficulty
                var hintsReamining = gameModel.hintsRemaining
                var score = gameModel.score
                var finished = false
                var isStarted = true
                var player = gameData.player

                var game = Game(gameId, score.toDouble(), finished, gameData.hintsEnabled, gameDif, hintsReamining, player, isStarted)

                for (question in gameModel.gameQuestions){
                    db.gameDao().UpdateQuestion(question.question)
                }
                db.gameDao().UpdateGame(game)

            }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
            alertDialog.show()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)

        var db = Room.databaseBuilder(applicationContext, GameDatabase::class.java ,"GameDatabase").createFromAsset("database/GameDatabase.db").allowMainThreadQueries().build()

        val gameModel: GameModel by viewModels()

        txtQuestion = findViewById(R.id.txt_pregunta)
        btnAnswer1 = findViewById(R.id.btn_respuesta1)
        btnAnswer2 = findViewById(R.id.btn_respuesta2)
        btnAnswer3 = findViewById(R.id.btn_respuesta3)
        btnAnswer4 = findViewById(R.id.btn_respuesta4)
        btnHint = findViewById(R.id.btn_hint)
        txtQuestionNumber = findViewById(R.id.txt_numPreguntas)
        txtSubject = findViewById(R.id.txt_tema)
        txtRemainingHints = findViewById(R.id.txt_pistasRestantes)
        btnPrev = findViewById(R.id.btn_prev)
        btnNext = findViewById(R.id.btn_next)
        btnFinish = findViewById(R.id.btn_finish)
        val extras = intent.extras
        if (extras != null) {
            gameModel.gameDifficulty = extras.getInt(DIFICULTY)
        }
        val buttons = listOf<Button>(btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4)

        if (gameModel.isEmpty) {

            var gameData = db.gameDao().getLastUnfinishedGame()
            gameModel.gameDifficulty = gameData.difficulty
            gameModel.score = gameData.score.toInt()
            if (!gameData.hintsEnabled){
                btnHint.isEnabled = false
                txtRemainingHints.isVisible = false
            }
            gameModel.hintsRemaining = gameData.hintsAvailable
            gameModel.NumberOfQuestions = db.gameDao().getNumberOfQuestionsByGameId(gameData.gameId)

            var questions = db.gameDao().GetQuestionsByGameId(gameData.gameId)

           for (question in questions){
               gameModel.gameQuestions.add(question)


               var answers = mutableListOf<String>()

               for (ans in question.answers){
                   if (ans.correct){
                       answers.add(ans.content)
                   }
               }
               while(answers.size <= gameData.difficulty){
                   var randQuestion = question.answers.random()
                   if(!answers.contains(randQuestion.content) && !randQuestion.correct){
                       answers.add(randQuestion.content)
                   }
               }
               gameModel.questionAnswers.add(answers)
           }
            gameModel.NumberOfQuestions = gameModel.gameQuestions.size
        }

        txtQuestionNumber.text = "${gameModel.questionNumber} / ${gameModel.TotalNumberOfQuestions}"

        txtQuestion.text = "${gameModel.currentQuestion.question.question}"

        txtSubject.text = gameModel.currentQuestion.question.topic
        txtRemainingHints.text = "Restantes: ${gameModel.getHints}"






        val answers = gameModel.GetQuestionAnswers()
        var preguntasElim = gameModel.gameDifficulty-1
        for (i in 3 downTo gameModel.gameDifficulty + 1) {
            buttons[i].visibility = View.GONE;
        }
        for (i in 0..gameModel.gameDifficulty) {
            buttons[i].text = answers[i]

        }

        for (button in buttons) {
            if (button.visibility != View.GONE) {
                button.setOnClickListener { _ ->
                    for (btn in buttons) {
                        if (btn.text != gameModel.currentQuestionAnswer) {
                            btn.setTextColor(Color.parseColor("#FFFFFF"))
                        } else {
                            btn.setBackgroundColor(Color.parseColor("#008000"))
                        }
                        btn.isClickable = false

                    }
                    gameModel.currentQuestion.question.selected = button.text.toString()
                    gameModel.currentQuestion.question.answered = true
                    gameModel.Answer()
                    if (gameModel.IsFinished)
                    {
                        btnFinish.visibility = View.VISIBLE
                    }
                    if (button.text != gameModel.currentQuestionAnswer) {
                        button.setBackgroundColor(Color.parseColor("#FF0000"))
                        gameModel.restartConsecutiveAnsweredCorrectly()
                    } else {
                        button.setBackgroundColor(Color.parseColor("#008000"))
                        button.setTextColor(Color.parseColor("#FFFFFF"))
                        gameModel.addScore()
                        gameModel.addQuestionAnsweredCorrectly()
                        if(!gameModel.questionHints) {
                            if(gameModel.getConsecutiveAnswersCorrectly<2) gameModel.addConsecutiveAnswerCorrectly()
                        }
                        if(gameModel.getConsecutiveAnswersCorrectly == 2){
                            gameModel.addHint()
                            txtRemainingHints.text = "Restantes: ${gameModel.getHints}"
                            gameModel.restartConsecutiveAnsweredCorrectly()
                        }
                    }
                }
            }
        }

        btnFinish.setOnClickListener {
            val act4 = Intent(this,ResultsActivity::class.java)
            act4.putExtra(SCORE, gameModel.getScore)
            act4.putExtra(DIFICULTY, gameModel.getDifficultyString())
            act4.putExtra(CORRECTAS, gameModel.getQuestionsAnsweredCorrectly)
            //act4.putExtra(HINTS, gameModel.getHintsUsed)

            var gameData = db.gameDao().getLastUnfinishedGame()
            var gameId = gameData.gameId
            var gameDif = gameData.difficulty
            var hintsReamining = gameModel.hintsRemaining
            var score = gameModel.score
            var finished = true
            var isStarted = true
            var player = gameData.player

            var game = Game(gameId, score.toDouble(), finished, gameData.hintsEnabled, gameDif, hintsReamining, player, isStarted)

            db.gameDao().UpdateGame(game)
            startActivity(act4)

        }
        btnNext.setOnClickListener {
            gameModel.nextQuestion()
            preguntasElim = gameModel.gameDifficulty-1
            txtQuestion.text = gameModel.currentQuestionText
            txtQuestionNumber.text =
                "${gameModel.questionNumber} / ${gameModel.TotalNumberOfQuestions}"
            txtSubject.text = gameModel.currentQuestion.question.topic

            val answers = gameModel.GetQuestionAnswers()

            for (i in 3 downTo gameModel.gameDifficulty + 1) {
                buttons[i].visibility = View.GONE;
            }

            if (!gameModel.currentQuestion.question.answered) {
                for (i in 0..gameModel.gameDifficulty) {
                    buttons[i].text = answers[i]
                    buttons[i].setBackgroundColor(Color.parseColor("#000000"))
                    buttons[i].setTextColor(Color.parseColor("#FFFFFF"))
                    buttons[i].isClickable = true
                }

            } else {
                for (i in 0..gameModel.gameDifficulty) {
                    buttons[i].setBackgroundColor(Color.parseColor("#000000"))
                    buttons[i].setTextColor(Color.parseColor("#FFFFFF"))
                    buttons[i].text = answers[i]
                    buttons[i].isClickable = false
                    if (buttons[i].text == gameModel.currentQuestion.question.selected && buttons[i].text == gameModel.currentQuestionAnswer) {
                        buttons[i].setBackgroundColor(Color.parseColor("#008000"))
                    } else {
                        if (buttons[i].text == gameModel.currentQuestionAnswer)
                            buttons[i].setBackgroundColor(Color.parseColor("#008000"))
                        if (buttons[i].text == gameModel.currentQuestion.question.selected) {
                            buttons[i].setBackgroundColor(Color.parseColor("#FF0000"))
                        }

                    }

                }

            }
        }
        btnPrev.setOnClickListener {
            gameModel.prevQuestion()
            preguntasElim = gameModel.gameDifficulty-1
            txtQuestion.text = gameModel.currentQuestionText
            txtQuestionNumber.text =
                "${gameModel.questionNumber} / ${gameModel.TotalNumberOfQuestions}"
            txtSubject.text = gameModel.currentQuestion.question.topic

            val answers = gameModel.GetQuestionAnswers()

            for (i in 3 downTo gameModel.gameDifficulty + 1) {
                buttons[i].visibility = View.GONE;
            }

            if (!gameModel.currentQuestion.question.answered) {
                for (i in 0..gameModel.gameDifficulty) {
                    buttons[i].text = answers[i]


                    buttons[i].setBackgroundColor(Color.parseColor("#000000"))
                    buttons[i].setTextColor(Color.parseColor("#FFFFFF"))
                    buttons[i].isClickable = true
                }

            } else {
                for (i in 0..gameModel.gameDifficulty) {
                    buttons[i].setBackgroundColor(Color.parseColor("#000000"))
                    buttons[i].setTextColor(Color.parseColor("#FFFFFF"))
                    buttons[i].text = answers[i]
                    buttons[i].isClickable = false
                    if (buttons[i].text == gameModel.currentQuestion.question.selected && buttons[i].text == gameModel.currentQuestionAnswer) {
                        buttons[i].setBackgroundColor(Color.parseColor("#008000"))
                    } else {
                        if (buttons[i].text == gameModel.currentQuestionAnswer)
                            buttons[i].setBackgroundColor(Color.parseColor("#008000"))
                        if (buttons[i].text == gameModel.currentQuestion.question.selected) {
                            buttons[i].setBackgroundColor(Color.parseColor("#FF0000"))
                        }

                    }

                }

            }
        }

        btnHint.setOnClickListener{
            if(!gameModel.currentQuestion.question.answered){
                for(i in 0..gameModel.gameDifficulty){
                    if(gameModel.getHints > 0){
                        if (buttons[i].text == gameModel.currentQuestionAnswer && preguntasElim == 0){
                            buttons[i].performClick()
                            gameModel.useHint()
                            txtQuestion.text = gameModel.currentQuestionText
                            txtRemainingHints.text = "Restantes: ${gameModel.getHints}"
                            break
                        }
                        if (buttons[i].text != gameModel.currentQuestionAnswer && buttons[i].isClickable && preguntasElim > 0){
                            preguntasElim -= 1
                            buttons[i].isClickable = false
                            buttons[i].setBackgroundColor(Color.parseColor("#444444"))
                            gameModel.useHint()
                            txtQuestion.text = gameModel.currentQuestionText
                            txtRemainingHints.text = "Restantes: ${gameModel.getHints}"
                            break
                        }
                    }
                }
            }else{
                Toast.makeText(this, "Ya has contestado esta pregunta", Toast.LENGTH_SHORT).show()
            }


        }

    }


}



