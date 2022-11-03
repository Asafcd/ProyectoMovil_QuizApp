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

    @Transaction
    @Query("SELECT * FROM questions WHERE topic = :topic")
    fun GetQuestionsByTopic(topic: String): List<QuestionWithAnswers>

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
    fun getAnswersForQuestion(questionId: Int): List<Answer>

    @Transaction
    @Query("SELECT Finished FROM games WHERE gameId=(SELECT max(gameId) FROM games) ")
    fun isFinished(): Boolean

    @Transaction
    @Query("SELECT count(1) WHERE exists (SELECT * FROM games) ")
    fun Size(): Int

    @Transaction
    @Query("INSERT INTO games(score,finished,hints_enabled,difficulty,hints_available,player,isStarted) values(:score,:finished,:hintsEnabled, :difficulty, :hintsAvailable, :player, :isStarted)")
    fun AddGame(score: Double, finished: Boolean, hintsEnabled: Boolean, difficulty: Int, hintsAvailable: Int, player: String, isStarted: Boolean)


}

//val score: Double,
//val finished: Boolean,
//@ColumnInfo(name = "hints_enabled") val hintsEnabled: Boolean,
//val difficulty: Int,
//@ColumnInfo(name = "hints_available") val hintsAvailable: Int,
//val player: String,
//val isStarted: Boolean)