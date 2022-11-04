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

    @Transaction
    @Query("SELECT * FROM questions WHERE questionId IN (SELECT questionId FROM game_questions WHERE gameId = :gameId)")
    fun GetQuestionsByGameId(gameId: Int): List<QuestionWithAnswers>

    @Transaction
    @Query("SELECT * FROM questions WHERE topic in(:topics) ")
    fun GetQuestionsByTopics(topics: List<String>): List<QuestionWithAnswers>

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


    @Query("SELECT * FROM answers WHERE questionId = :questionId ")
    fun getAnswersForQuestion(questionId: Int): List<Answer>

    @Transaction
    @Query("SELECT Finished FROM games WHERE gameId=(SELECT max(gameId) FROM games) ")
    fun isFinished(): Boolean


    @Query("SELECT count(gameId) FROM games")
    fun Size(): Int

    @Query("INSERT INTO games(score,finished,hints_enabled,difficulty,hints_available,player,isStarted) values(:score,:finished,:hintsEnabled, :difficulty, :hintsAvailable, :player, :isStarted)")
    fun AddGame(score: Double, finished: Boolean, hintsEnabled: Boolean, difficulty: Int, hintsAvailable: Int, player: String, isStarted: Boolean)

    @Query("SELECT * FROM games ORDER BY gameId DESC LIMIT 1")
    fun getLastUnfinishedGame(): Game

    @Query("SELECT gameId FROM games ORDER BY gameId DESC LIMIT 1")
    fun getLastUnfinishedGameId(): Int

    @Query("SELECT COUNT(questionId) FROM game_questions WHERE gameId = :gameId")
    fun getNumberOfQuestionsByGameId(gameId: Int): Int

    @Query("INSERT INTO game_questions Values(:gameId, :questionId)")
    fun addGameQuestions(gameId: Int, questionId: Int)
}

//val score: Double,
//val finished: Boolean,
//@ColumnInfo(name = "hints_enabled") val hintsEnabled: Boolean,
//val difficulty: Int,
//@ColumnInfo(name = "hints_available") val hintsAvailable: Int,
//val player: String,
//val isStarted: Boolean)