package com.example.proyecto

import org.w3c.dom.Text

data class Question(val question: String, val answer: String, val wrong_answers: List<String>, val topic: String, var answered: Boolean, var selected: String?)


