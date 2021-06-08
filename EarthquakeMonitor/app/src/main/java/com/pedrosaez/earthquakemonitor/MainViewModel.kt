package com.pedrosaez.earthquakemonitor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.json.JSONObject

class MainViewModel : ViewModel() {

    /*private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
*/

    // creamos el mutable livedata que usaremois solo en esta clase y por eso es privado
    private var _eqList = MutableLiveData<MutableList<Earthquake>>()

    // creamos el livedata que podrán usarlo otras clases pero que al no ser mutable no se puede modificar
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList // le damos el valor de la mutablelist


    //se ejecuta automáticamente al crear el viewmodel
    init {
        // creamos una corrutina ligada al viewmodel
        viewModelScope.launch {
            _eqList.value = fetchEarthquakes()
        }
    }

    // la función es suspend para poder usarla dentro de un corrutina
    private suspend fun fetchEarthquakes(): MutableList<Earthquake>? {
        //usamos  el contexto IO para hacer el trabajo pesado y no interrumpir el main
        return withContext(Dispatchers.IO) {
            val eqListString:String = service.getLastHourEarthquakes() // aqui estamos llamando al
            // servicio de retrofit para traer los datos
            val eqList =parseEqResult(eqListString)
            eqList

        }
    }

    private fun parseEqResult(eqListString: String): MutableList<Earthquake> {

        // Obtenemos el objeto Json
        val eqJsonObject = JSONObject(eqListString)

        //ver la web https://earthquake.usgs.gov/earthquakes/feed/v1.0/geojson.php

        //obtenemos el json array de features para obtener los datos
        val featuresJsonArray = eqJsonObject.getJSONArray("features")

        //creamos lista vacia para ir agregando terremotos
        val eqList = mutableListOf<Earthquake>()

        // al ser un array debemos iterar para obtener los datos
        //features es un array que conitne otros objetos json
        for(i in 0 until featuresJsonArray.length()){

            //obtenemos el objeto parseandolo a objeto json
            val featuresJSONObject = featuresJsonArray[i] as JSONObject
            val id= featuresJSONObject.getString("id")

            val propertiesJsonObjects = featuresJSONObject.getJSONObject("properties")
            val mag= propertiesJsonObjects.getDouble("mag")
            val place= propertiesJsonObjects.getString("place")
            val time= propertiesJsonObjects.getLong("time")

            val geometryJsonObject = featuresJSONObject.getJSONObject("geometry")
            val coordinatesJsonArray= geometryJsonObject.getJSONArray("coordinates")
            val longitude = coordinatesJsonArray.getDouble(0)
            val latitude = coordinatesJsonArray.getDouble(1)

            val earthquake = Earthquake(id,place,mag,time,longitude,latitude)
            eqList.add(earthquake)
        }
        return eqList

    }
}