package com.pedrosaez.fakebot


import android.os.Handler

import android.view.View

import android.widget.EditText
import android.widget.TextView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class MainViewModel : ViewModel() {
    private var _chatMessageListLiveData = MutableLiveData<MutableList<Frases>>()
    val chatMessageListLiveData: LiveData<MutableList<Frases>>
        get() = _chatMessageListLiveData


    private var handler: Handler = Handler()

    init {
        _chatMessageListLiveData.value = mutableListOf()
    }


    private val respuestas: Array<String> = arrayOf("Si",
            "No",
            "Pregunta de Nuevo",
            "Es muy probable",
            "No lo creo",
            "No lo sé")


    fun addFraseUsuario(frase: Frases) {
        val mutableList = _chatMessageListLiveData.value!!
        mutableList.add(frase)
        _chatMessageListLiveData.value = mutableList
    }

    fun addFraseBot() {
        val runnable = Runnable {
            val random = Random().nextInt(respuestas.size)

            val mutableList = _chatMessageListLiveData.value!!
            val respuestas = respuestas.random()
            val frase = Frases(respuestas, false)
            mutableList.add(frase)
            _chatMessageListLiveData.value = mutableList
        }
        handler.postDelayed(runnable, 1500)

    }



}







