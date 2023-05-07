package com.example.sumandodeprisa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts.*

class MainActivity : AppCompatActivity() {
    private lateinit var etSegundos: EditText
    private lateinit var btInicio: Button
    private lateinit var tvRespuesta: TextView

    private val responseLauncher = registerForActivityResult(StartActivityForResult()) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            val esCorrecto = activityResult.data?.getBooleanExtra("esCorrecto", false) ?: false
            if (esCorrecto) {
                tvRespuesta.text = getString(R.string.acierto)
            } else {
                tvRespuesta.text = getString(R.string.fallo)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSegundos = findViewById(R.id.etSegundos)
        btInicio = findViewById(R.id.btInicio)
        tvRespuesta = findViewById(R.id.tvRespuesta)

        btInicio.setOnClickListener{
            val segundos = etSegundos.text.toString().toInt()
            val intent = Intent(this, PruebaMatematica::class.java)
            intent.putExtra("segundos", segundos)

            responseLauncher.launch(intent)
        }
    }
}