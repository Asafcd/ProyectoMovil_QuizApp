package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner



class MainMenuActivity : AppCompatActivity() {

    private lateinit var spin: Spinner
    private lateinit var btn_opciones: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        spin = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.Dificultad,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spin.adapter = adapter
        }

        btn_opciones = findViewById<Button>(R.id.btn_opciones)
        btn_opciones.setOnClickListener {
            val act2 = Intent(this,OptionsMenuActivity::class.java)
            startActivity(act2)
        }

    }
}