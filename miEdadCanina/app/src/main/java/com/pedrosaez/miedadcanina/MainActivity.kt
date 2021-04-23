package com.pedrosaez.miedadcanina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val ageEdit = findViewById<EditText>(R.id.age_edit)
        val resultText = findViewById<TextView>(R.id.result_text)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val ageInt = ageEdit.text.toString().toInt()
            resultText.text = "Tu edad canina es de ${(ageInt * 7).toString()} años"

        }


    }
}