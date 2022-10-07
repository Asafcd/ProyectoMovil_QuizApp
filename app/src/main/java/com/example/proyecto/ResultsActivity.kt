package com.example.proyecto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.IOException
import java.net.URL

class ResultsActivity : AppCompatActivity() {
    lateinit var txtPuntuacion: TextView
    lateinit var txtCorrectas: TextView
    lateinit var txtDificultad: TextView
    lateinit var txtHintsUsadas: TextView
    lateinit var imgResult: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        txtPuntuacion = findViewById(R.id.txt_puntuacion)
        txtCorrectas = findViewById(R.id.txt_correctas)
        txtDificultad = findViewById(R.id.txt_dificultadUsada)
        txtHintsUsadas = findViewById(R.id.txt_pistasUsadas)
        imgResult = findViewById(R.id.img_result)


        val extras = intent.extras
        if (extras != null) {
            txtPuntuacion.text = "Puntaje: ${extras.getInt(SCORE)}"
            txtDificultad.text = "Dificultad: ${extras.getString(DIFICULTY)}"
            txtCorrectas.text = "Correctas: ${extras.getInt(CORRECTAS)}"
            txtHintsUsadas.text = "Pistas Usadas: ${extras.getInt(HINTS)}"
        }

//        val url = URL(
//            "https://images.pexels.com/photos/954126/" +
//                    "pexels-photo-954126.jpeg?auto=compress&cs=" +
//                    "tinysrgb&dpr=2&h=750&w=1260"
//        )
//        urlToImageView(imgResult, url)


    }

//    private fun urlToImageView(imageView: ImageView, urlImage: URL) {
//        // Async task to get bitmap from url
//        val result: Deferred<Bitmap?> = GlobalScope.async {
//            try {
//                BitmapFactory.decodeStream(urlImage.openStream())
//            } catch (e: IOException) {
//                null
//            }
//        }
//    }
}