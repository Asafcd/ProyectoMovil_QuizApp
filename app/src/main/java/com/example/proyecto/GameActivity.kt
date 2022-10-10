package com.example.proyecto

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

val SCORE = "SCORE"
val CORRECTAS = "CORRECTAS"
val HINTS = "HINTS"

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)

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
            gameModel.GetRandomQuestions(gameModel.gameDifficulty)
        }

        txtQuestionNumber.text = "${gameModel.questionNumber} / ${gameModel.TotalNumberOfQuestions}"

        txtQuestion.text = "${gameModel.currentQuestion.question}"

        txtSubject.text = gameModel.currentQuestion.topic
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
                    gameModel.currentQuestion.selected = button.text.toString()
                    gameModel.currentQuestion.answered = true
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
                        if(gameModel.getConsecutiveAnswersCorrectly<2) gameModel.addConsecutiveAnswerCorrectly()

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
            act4.putExtra(HINTS, gameModel.getHintsUsed)
            startActivity(act4)

        }
        btnNext.setOnClickListener {
            gameModel.nextQuestion()
            preguntasElim = gameModel.gameDifficulty-1
            txtQuestion.text = gameModel.currentQuestionText
            txtQuestionNumber.text =
                "${gameModel.questionNumber} / ${gameModel.TotalNumberOfQuestions}"
            txtSubject.text = gameModel.currentQuestion.topic

            val answers = gameModel.GetQuestionAnswers()

            for (i in 3 downTo gameModel.gameDifficulty + 1) {
                buttons[i].visibility = View.GONE;
            }

            if (!gameModel.currentQuestion.answered) {
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
                    if (buttons[i].text == gameModel.currentQuestion.selected && buttons[i].text == gameModel.currentQuestionAnswer) {
                        buttons[i].setBackgroundColor(Color.parseColor("#008000"))
                    } else {
                        if (buttons[i].text == gameModel.currentQuestionAnswer)
                            buttons[i].setBackgroundColor(Color.parseColor("#008000"))
                        if (buttons[i].text == gameModel.currentQuestion.selected) {
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
            txtSubject.text = gameModel.currentQuestion.topic

            val answers = gameModel.GetQuestionAnswers()

            for (i in 3 downTo gameModel.gameDifficulty + 1) {
                buttons[i].visibility = View.GONE;
            }

            if (!gameModel.currentQuestion.answered) {
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
                    if (buttons[i].text == gameModel.currentQuestion.selected && buttons[i].text == gameModel.currentQuestionAnswer) {
                        buttons[i].setBackgroundColor(Color.parseColor("#008000"))
                    } else {
                        if (buttons[i].text == gameModel.currentQuestionAnswer)
                            buttons[i].setBackgroundColor(Color.parseColor("#008000"))
                        if (buttons[i].text == gameModel.currentQuestion.selected) {
                            buttons[i].setBackgroundColor(Color.parseColor("#FF0000"))
                        }

                    }

                }

            }
        }

        btnHint.setOnClickListener{
            if(!gameModel.currentQuestion.answered){
                for(i in 0..gameModel.gameDifficulty){
                    if(gameModel.getHints > 0){
                        if (buttons[i].text == gameModel.currentQuestionAnswer && preguntasElim == 0){
                            buttons[i].performClick()
                            gameModel.useHint()
                            gameModel.restartConsecutiveAnsweredCorrectly()
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