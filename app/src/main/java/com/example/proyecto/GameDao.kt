package com.example.proyecto

import androidx.room.*


@Dao
interface GameDao {

    @Transaction
    @Query("SELECT * FROM games")
    fun getGameWithQuestions(): List<GameWithQuestions>

    @Transaction
    @Query("SELECT * FROM questions")
    fun GetAllQuesitonsWithAnswers(): List<QuestionWithAnswers>

    @Query("SELECT * FROM questions WHERE topic = :topic")
    fun GetQuestionsByTopic(topic: String)

    @Insert
    fun AddQuestion(question: Question)

    @Insert
    fun AddQuestions(questions: List<Question>)

    @Delete
    fun DeleteQuestion(question: Question)

    @Delete
    fun DeleteQuestions(questions: List<Question>)

    @Update
    fun UpdateQuestion(question: Question)

    @Update
    fun UpdateQuestions(vararg questions: Question)

    @Transaction
    @Query("SELECT * FROM answers WHERE questionId = :questionId ")
    fun getAnswersForQuestion(questionId: Int)


}