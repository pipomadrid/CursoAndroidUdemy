package com.pedrosaez.earthquakemonitor.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.pedrosaez.earthquakemonitor.Earthquake
import com.pedrosaez.earthquakemonitor.database.getDatabase
import kotlinx.coroutines.*
import java.net.UnknownHostException


private  val TAG = MainViewModel::class.java.simpleName
class MainViewModel(application: Application): AndroidViewModel(application) {

    /*private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
*/

    private val database = getDatabase(application.applicationContext)
    private var repository = MainRepository(database)

    val eqList = repository.eqList
    //se ejecuta automáticamente al crear el viewmodel
    init {
        // creamos una corrutina ligada al viewmodel
        viewModelScope.launch {

            try {
                repository.fetchEarthquakes()
            }catch (e :UnknownHostException){
                Log.d(TAG, "No internet connection", e)

            }
        }
    }








}