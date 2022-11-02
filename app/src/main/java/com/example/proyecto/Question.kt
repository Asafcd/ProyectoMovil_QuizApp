package com.example.proyecto

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity
data class Question(
    @PrimaryKey val questionId: Int,
    var question: String,
    val topic: String,
    var answered: Boolean,
    var selected: String?,
    var hintsUsed: Boolean)


