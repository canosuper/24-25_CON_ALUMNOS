package com.example.bindingimagesseekrbswitch

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bindingimagesseekrbswitch.R
import com.example.bindingimagesseekrbswitch.databinding.ActivityMainBinding
import modelo.PedidoPizzeria

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //variables locales
        val miTag = "Antonio"
        var currentImage = R.mipmap.ic_comida
        val image1 = R.mipmap.ic_comida
        val image2 = R.drawable.ic_pizza

        binding.btAceptar.setOnClickListener {

            var mensaje =""
            val nombre = binding.ptNombre.text?.toString() ?: "" // Provide a default value if null

            if(binding.swLicencia.isChecked){

                var pide=false
                mensaje = "Hola $nombre, has pedido una pizza con "
                if(binding.chQueso.isChecked){
                    mensaje += "Queso "
                    pide=true
                }
                if(binding.chCebolla.isChecked){
                    mensaje += "Cebolla "
                    pide=true
                }
                if(binding.chBacon.isChecked){
                    mensaje += "Bacon "
                    pide=true
                }
                if(!pide){
                    mensaje += "ningun ingrediente "
                }
                if(binding.rdFino.isChecked){
                    mensaje += "y de borde fino"
                }
                if(binding.rdGordo.isChecked){
                    mensaje += "y de borde ancho"
                }
                val pedido = PedidoPizzeria(nombre, binding.chQueso.isChecked, binding.chBacon.isChecked,
                    binding.chCebolla.isChecked, binding.rdFino.isChecked,binding.rdGordo.isChecked, binding.sbSatisfaccion.progress)
                Log.i(miTag, pedido.toString())
            }else{
                mensaje = "Debes aceptar la licencia"
            }

            Log.i(miTag,mensaje)
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()


        }
        binding.btBorrar.setOnClickListener {
            binding.ptNombre.text.clear()
            binding.swLicencia.isChecked=false
            binding.chQueso.isChecked=false
            binding.chCebolla.isChecked=false
            binding.chBacon.isChecked=false
            binding.rdFino.isChecked=true
            binding.rdGordo.isChecked=false
            binding.sbSatisfaccion.progress=0
        }
        binding.ibPlay.setOnClickListener {
            if (currentImage == image1) {
                binding.ivPrincipal.setImageResource(image2)
                currentImage = image2
            } else {
                binding.ivPrincipal.setImageResource(image1)
                currentImage = image1
            }
        }

        binding.ivPrincipal.setOnClickListener {
            binding.ivPrincipal.setImageResource(R.drawable.pizza)
        }

        binding.sbSatisfaccion.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Called when the progress level has changed.
                // Use the progress value here (ranges from 0 to max value)
                Log.i(miTag, "Progress: $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when the user starts changing the progress value.
                Log.i(miTag, "Start tracking ${binding.sbSatisfaccion.progress}")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when the user stops changing the progress value.
                Log.i(miTag, "Stop tracking ${binding.sbSatisfaccion.progress}")
            }
        })
    }
}