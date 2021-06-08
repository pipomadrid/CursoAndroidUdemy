package com.pedrosaez.earthquakemonitor.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrosaez.earthquakemonitor.Earthquake
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    /*private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
*/

    // creamos el mutable livedata que usaremois solo en esta clase y por eso es privado
    private var _eqList = MutableLiveData<MutableList<Earthquake>>()

    // creamos el livedata que podrán usarlo otras clases pero que al no ser mutable no se puede modificar
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList // le damos el valor de la mutablelist

    private var repository = MainRepository()
    //se ejecuta automáticamente al crear el viewmodel
    init {
        // creamos una corrutina ligada al viewmodel
        viewModelScope.launch {
            _eqList.value = repository.fetchEarthquakes()
        }
    }








}