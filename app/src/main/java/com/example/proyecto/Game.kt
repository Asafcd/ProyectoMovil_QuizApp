package com.example.proyecto

import android.content.IntentSender
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "games")
data class Game(
    @PrimaryKey var gameId: Int,
    val score: Double,
    val finished: Boolean,
    @ColumnInfo (name = "hints_enabled") val hintsEnabled: Boolean,
    val difficulty: Int,
    @ColumnInfo (name = "hints_availble") val hintsAvailable: Int,
    val player: String)
