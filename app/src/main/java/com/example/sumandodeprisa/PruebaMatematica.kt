package com.example.sumandodeprisa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.random.Random

class PruebaMatematica : AppCompatActivity() {
    private lateinit var btSalir: Button
    private lateinit var pB: ProgressBar
    private lateinit var tvNumero1: TextView
    private lateinit var tvNumero2: TextView
    private lateinit var etRespuesta: EditText
    private lateinit var btResultado: Button

    private var resultado = 0
    private var segundos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_matematica)

        btSalir = findViewById(R.id.btSalir)
        pB = findViewById(R.id.pB)
        tvNumero1 = findViewById(R.id.tvNumero1)
        tvNumero2 = findViewById(R.id.tvNumero2)
        etRespuesta = findViewById(R.id.etRespuesta)
        btResultado = findViewById(R.id.btResultado)

        segundos = intent.getIntExtra("segundos", 0)
        pB.max = segundos

        generarNumeros()

        val temporizador = object : CountDownTimer(segundos * 1000L, 1000) {
            override fun onTick(milesimasRestantes: Long) {
                val segundosRestantes = (milesimasRestantes / 1000).toInt()
                pB.progress = segundos - segundosRestantes
            }

            override fun onFinish() {
                verificarRespuesta()
            }
        }
        temporizador.start()

        btResultado.setOnClickListener {
            verificarRespuesta()
            finish()
        }

        btSalir.setOnClickListener {
            verificarRespuesta()
            finish()
        }
    }

    private fun generarNumeros() {
        val numero1 = Random.nextInt(100_000, 999_999)
        val numero2 = Random.nextInt(100_000, 999_999)
        resultado = numero1 + numero2

        tvNumero1.text = numero1.toString()
        tvNumero2.text = numero2.toString()
    }

    private fun verificarRespuesta() {
        val respuestaUsuario = etRespuesta.text.toString().toIntOrNull()
        if (respuestaUsuario == resultado) {
            setResult(RESULT_OK, Intent().apply { putExtra("esCorrecto", true) })
        } else {
            setResult(RESULT_OK, Intent().apply { putExtra("esCorrecto", false) })
        }
    }
}