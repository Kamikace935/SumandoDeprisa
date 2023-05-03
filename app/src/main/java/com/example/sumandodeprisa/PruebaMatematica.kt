package com.example.sumandodeprisa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar

class PruebaMatematica : AppCompatActivity() {
    lateinit var pB: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_matematica)

        pB = findViewById(R.id.pB)

    }
}