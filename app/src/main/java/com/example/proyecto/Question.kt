package com.example.proyecto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity (tableName = "questions")
data class Question (
    @PrimaryKey (autoGenerate = true)
    val questionId: Int,
    var question: String,
    val topic: String,
    var answered: Boolean,
    var selected: String?,
    @ColumnInfo(name = "hints_used") var hintsUsed: Boolean
    )


