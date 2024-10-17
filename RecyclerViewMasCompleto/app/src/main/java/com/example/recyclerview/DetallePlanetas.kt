package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import modelo.Planet
import modelo.PlanetData

class DetallePlanetas : AppCompatActivity() {
    val planets = PlanetData.getPlanets()
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_planetas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val mlPlanetas: EditText = findViewById(R.id.mlPlanetas)
        val btVolver: Button = findViewById(R.id.btVolver)
        val selectedItems = intent.getIntegerArrayListExtra("selectedItems") ?: emptyList<Int>()

        // Access selected planets using selectedItems and display details
        val selectedPlanets = selectedItems.map { planets[it] } // Assuming 'planets' is accessible here
        // ... display details of selectedPlanets ...
        Log.i("ACSCO", "Selected planets: ${selectedPlanets}")
        val planetDetails = selectedPlanets.joinToString("\n") { planet ->
            "Name: ${planet.name}, Diameter: ${planet.sizeKm}, Distance: ${planet.distanceAU}"
        }

        mlPlanetas.setText(planetDetails)
        btVolver.setOnClickListener {
            finish()
        }



    }
}