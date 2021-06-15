package com.pedrosaez.earthquakemonitor.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.pedrosaez.earthquakemonitor.Earthquake
import com.pedrosaez.earthquakemonitor.api.ApiResponseStatus
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

    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get()= _status

    private var _eqList = MutableLiveData<MutableList<Earthquake>>()
    val eqList : LiveData<MutableList<Earthquake>>
        get()= _eqList

    //se ejecuta automáticamente al crear el viewmodel
    init {
        realoadEarthquakes(false)
    }

     private fun realoadEarthquakes(sortByMagnitude :Boolean) {
         viewModelScope.launch {
             try {
                 _status.value = ApiResponseStatus.LOADING
                 _eqList.value = repository.fetchEarthquakes(sortByMagnitude)
                 _status.value = ApiResponseStatus.DONE
             } catch (e: UnknownHostException) {
                 _status.value = ApiResponseStatus.NOT_INTERNET_CONNECTION
                 Log.d(TAG, "No internet connection", e)

             }
         }
    }
    fun realoadEarthquakesFromDatabase(sortByMagnitude :Boolean){
        viewModelScope.launch {
                _eqList.value = repository.fetchEarthquakesFromDb(sortByMagnitude)

        }

    }


}