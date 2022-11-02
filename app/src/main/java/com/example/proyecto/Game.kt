package com.example.proyecto

import android.content.IntentSender
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "games")
data class Game(
    @PrimaryKey (autoGenerate = true)
    var gameId: Int,
    val score: Double,
    val finished: Boolean,
    @ColumnInfo (name = "hints_enabled") val hintsEnabled: Boolean,
    val difficulty: Int,
    @ColumnInfo (name = "hints_available") val hintsAvailable: Int,
    val player: String,
    val isStarted: Boolean)
