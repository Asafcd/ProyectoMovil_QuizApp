package com.example.proyecto

class GameModel {
    private val allQuestions = mutableListOf<Question>()
    private var gameQuestions = mutableListOf<Question>()

    private var currentQuestionIndex = 0



    val currentQuestion: Question
        get() = gameQuestions[currentQuestionIndex]

    val currentQuestionText: String
        get() = gameQuestions[currentQuestionIndex].question

    val currentQuestionAnswer: String
        get() = gameQuestions[currentQuestionIndex].answer


}