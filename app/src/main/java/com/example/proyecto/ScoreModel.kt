package com.example.proyecto

import androidx.lifecycle.ViewModel

class ScoreModel: ViewModel() {
    var score = 0.0
    var player ="frn"

    val getScore: Double
        get() = score

    val getInit: String
        get() = player

}