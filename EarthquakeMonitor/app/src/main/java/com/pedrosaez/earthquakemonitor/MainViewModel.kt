package com.pedrosaez.earthquakemonitor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    /*private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
*/
    private var _eqList = MutableLiveData<MutableList<Earthquake>>()
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList

    init {
        viewModelScope.launch {
            _eqList.value = fetchEarthquakes()
        }
    }

    private suspend fun fetchEarthquakes(): MutableList<Earthquake>? {
        return withContext(Dispatchers.IO) {
            val eqListString:String = service.getLastHourEarthquakes()
            mutableListOf<Earthquake>()

        }
    }
}