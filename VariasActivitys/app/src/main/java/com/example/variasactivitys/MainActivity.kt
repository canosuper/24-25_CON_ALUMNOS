package com.example.variasactivitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.variasactivitys.databinding.ActivityMainBinding
import modelo.Almacen
import modelo.Persona

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    //Esta variable es necesaria para la llamada y espera de forma actual.
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            // Get String data from Intent
            val returnString = data!!.getStringExtra("valorEdicionV2")
            //val returnString = data!!.getSerializableExtra("objeto")
            // Set text view with string
            binding.edNombre.setText(returnString)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btVentana2.setOnClickListener {

            //Con intent y pares clave - valor
            var inte : Intent = Intent(this, Ventana2::class.java)
//            inte.putExtra("nombre1",binding.edNombre.text.toString())
//            inte.putExtra("edad1",binding.edEdad.text.toString())

            //Pasar un objeto a otra ventana

            var p = Persona(binding.edNombre.text.toString(), binding.edEdad.text.toString().toInt())
//            inte.putExtra("obj",p)

            //ahora con Bundle
//            val bundle = Bundle()
//            bundle.putString("nombre1", binding.edNombre.text.toString())
//            bundle.putString("edad1", binding.edEdad.text.toString())
//            bundle.putSerializable("persona",p)
//            inte.putExtra("objeto",bundle)
            //ahora con on object
            Almacen.addPersona(p)
            startActivity(inte)

        }

        binding.btReiniciar.setOnClickListener {
            var ine : Intent = intent
            finish()
            startActivity(ine)
        }

        //Con este método llamamos a la segunda ventana y esperamos que nos devuelva algo.
        //Usamos la forma deprecated, pero todavía vigente, de: startActivityForResult.
        //Lo que nos devuelva la segunda ventana será tratado en el método onActivityResult (un poco más abajo).
        binding.btEsperaRespuestaDepre.setOnClickListener {
            // Start the SecondActivity
            val intent = Intent(this, Ventana2::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }

        //Método más actual para llamar a una ventana2 y esperar que esa ventana nos devuelva resultados.
        //Se necesita definir una variable (resultlancher) definida como atributo de la clase (ver más arriba).
        binding.btEsperarRespuestaActual.setOnClickListener {
            // Start the SecondActivity
            val intent = Intent(this, Ventana2::class.java)
            resultLauncher.launch(intent)
        }
    }

    // This method is called when the second activity finishes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                // Get String data from Intent
                val returnString = data!!.getStringExtra("keyName")

                // Set text view with string
                binding.edNombre.setText(returnString)
            }
            else{
                binding.edNombre.setText("No ha devuelto nada")
            }
        }
    }
}