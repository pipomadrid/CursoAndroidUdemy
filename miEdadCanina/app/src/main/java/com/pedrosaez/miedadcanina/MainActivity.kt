package com.pedrosaez.miedadcanina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.pedrosaez.miedadcanina.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    //Constante para usar en el log
    private val TAG = "MainActivity"
    //Creamos la variable binding apra vincular el xml con la lógica
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializamos binding inflándole el xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Vinculamos los views asignándolos a una variable
        val ageEdit = binding.ageEdit
        val resultText = binding.resultText
        val button = binding.button

        // log personalizado
        Log.d(TAG , "Activity created")

        button.setOnClickListener {
            //Si hay texto en el editText de la edad  ponemos la edad en el resultText
            //Si no lanzamos un toast indicando que no se ha puesto la edad
            if(ageEdit.text.isNotEmpty()) {
                val ageInt = ageEdit.text.toString().toInt()
                resultText.text = getString(R.string.result_text,ageInt * 7)
            }else{
                Toast.makeText(this,getString(R.string.you_must_insert_your_age),Toast.LENGTH_SHORT).show()
                Log.d(TAG,"Age is empty")
            }

        }


    }
}