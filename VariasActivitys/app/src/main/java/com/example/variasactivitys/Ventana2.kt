package com.example.variasactivitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.variasactivitys.databinding.ActivityVentana2Binding
import modelo.Almacen
import modelo.Persona

class Ventana2 : AppCompatActivity() {
    lateinit var binding: ActivityVentana2Binding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVentana2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //Con intent y pares clave - valor
        //var nom = intent.getStringExtra("nombre1")
        //var eda = intent.getStringExtra("edad1")
        //binding.txtCaja2.text = "Hola " + nom + " tienes " + eda + " años"
        //val p : Persona = intent.getSerializableExtra("obj") as Persona
        //binding.txtCaja2.text = p.toString()

       //con Bundle
//        val bundle = intent.getBundleExtra("objeto")
//        val nom = bundle!!.getString("nombre1")
//        val eda = bundle!!.getString("edad1")
//        val p = bundle!!.getSerializable("persona" )
//
//       // binding.txtCaja2.text = "Con Bundle dato a dato: "+nom+" "+eda
//        binding.txtCaja2.text = "Con Bundle el objeto: "+p.toString()

//        //ahora mediante el almacen
        binding.txtCaja2.text = Almacen.getPersonas().toString()

        binding.btVolver.setOnClickListener {
            finish()//este destruye este activity y vuelve a la ventana anterior
        }

        //Devolver datos a la ventana 1 de forma deprecated.
        binding.btDevolverDepre.setOnClickListener {
            // Get the text from the EditText
            val stringToPassBack = binding.edDevolver.text.toString()

            // Put the String to pass back into an Intent and close this activity
            val intent = Intent()
            intent.putExtra("keyName", stringToPassBack)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        //Devolver datos a la ventana 1 de forma actual.
        binding.btDevolverActual.setOnClickListener {
            // Get the text from the EditText
            val stringToPassBack = binding.edDevolver.text.toString()

            // Put the String to pass back into an Intent and close this activity
            val intent = Intent()
            intent.putExtra("valorEdicionV2", stringToPassBack)
            //val p:Persona = Persona(editText.text.toString())
            //intent.putExtra("objeto",p) //El objeto debe ser serailizable, para ello ver clase Pesona.kt

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

}