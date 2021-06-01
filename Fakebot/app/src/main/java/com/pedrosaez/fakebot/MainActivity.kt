package com.pedrosaez.fakebot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pedrosaez.fakebot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView = binding.fakeBotRecycler

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.chatMessageListLiveData.observe(this, Observer { chatMessageList ->
            val adapter = FbAdapter(this, chatMessageList)
            recyclerView.adapter = adapter
            recyclerView.scrollToPosition(chatMessageList.size - 1)
            binding.fakeBotNoMessages.visibility = if (chatMessageList.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })


        mHandler = Handler()

        val listaPreguntas = mutableListOf<Frases>()

        binding.fakebotIvSend.setOnClickListener {
            val fraseUsuario: String = binding.fakebotEtMessage.text.toString()


            if (fraseUsuario.isNotEmpty()) {
                val frase = Frases(fraseUsuario, true)
                viewModel.addFraseUsuario(frase)
                viewModel.addFraseBot()
                binding.fakebotEtMessage.setText("")
            } else {
                Toast.makeText(this, "Debes insertar un mensaje para enviarlo", Toast.LENGTH_SHORT).show()
            }


        }


        /*

        REPASAR TODOS LOS PASOS DE NUEVO 
         */
    }
}