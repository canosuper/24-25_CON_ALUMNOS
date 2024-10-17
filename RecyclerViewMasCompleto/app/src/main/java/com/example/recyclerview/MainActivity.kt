package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import modelo.Planet
import adaptador.PlanetAdapter
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import modelo.PlanetData

class MainActivity : AppCompatActivity() {
    val planets = PlanetData.getPlanets()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val botonDetalle: Button = findViewById(R.id.btDetalle)
        var  recyclerView: RecyclerView = findViewById(R.id.rvPlanetas)
        recyclerView.layoutManager =  LinearLayoutManager(this)
        val planetAdapter= PlanetAdapter(planets)
        recyclerView.adapter = planetAdapter



        botonDetalle.setOnClickListener {

            //Toast.makeText(this, "Selected items: ${selectedItems}", Toast.LENGTH_SHORT).show()
            val selectedItems = planetAdapter.getSelectedItems()
            val intent = Intent(this, DetallePlanetas::class.java)
            intent.putIntegerArrayListExtra("selectedItems", ArrayList(selectedItems))
            startActivity(intent)
        }

    }

}