package com.example.proyecto

import androidx.lifecycle.ViewModel

class ScoreModel: ViewModel() {
    var score = 0.0
    var player ="front"

    val getScore: Double
        get() = score

    val getInit: String
        get() = player

}