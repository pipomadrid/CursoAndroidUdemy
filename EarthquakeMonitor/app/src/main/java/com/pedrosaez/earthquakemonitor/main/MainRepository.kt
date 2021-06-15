package com.pedrosaez.earthquakemonitor.main

import androidx.lifecycle.LiveData
import com.pedrosaez.earthquakemonitor.Earthquake
import com.pedrosaez.earthquakemonitor.api.EqJsonResponse
import com.pedrosaez.earthquakemonitor.api.service
import com.pedrosaez.earthquakemonitor.database.EqDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val database: EqDatabase ) {

     suspend fun fetchEarthquakes(sortByMagnitude :Boolean) : MutableList<Earthquake> {
        //usamos  el contexto IO para hacer el trabajo pesado y no interrumpir el main
        return withContext(Dispatchers.IO) {
            // val eqListString:String = service.getLastHourEarthquakes() // aqui estamos llamando al
            // servicio de retrofit para traer los datos
            val eqJsonResponse= service.getLastHourEarthquakes()  //para usar en  moshi
            val eqList =parseEqResult(eqJsonResponse)

            database.eqDao.insertAll(eqList)
            fetchEarthquakesFromDb(sortByMagnitude)
            //val eqList =parseEqResult(eqListString)
        }
    }

    suspend fun fetchEarthquakesFromDb(sortByMagnitude :Boolean):MutableList<Earthquake> {
        return withContext(Dispatchers.IO) {
            if (sortByMagnitude) {
                database.eqDao.getEarthquakesByMagnitude()
            } else {
                database.eqDao.getEarthQuakes()
            }
        }
    }
    private fun parseEqResult(eqJsonResponse: EqJsonResponse): MutableList<Earthquake> {

        //creamos lista vacia para ir agregando terremotos
        val eqList = mutableListOf<Earthquake>()
        val featureList = eqJsonResponse.features
        for(feature in featureList){
            val properties = feature.properties

            val id = feature.id
            val magnitude =  properties.mag
            val place = properties.place
            val time = properties.time

            val geometry = feature.geometry
            val longitude = geometry.longitud
            val latitude = geometry.latitude

            eqList.add(Earthquake(id,place,magnitude,time,longitude,latitude))
        }

        return eqList
    }

    /* private fun parseEqResult(eqListString: String): MutableList<Earthquake> {

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
  */
    /*
    Se puede parsear json como lo hicimos , con Gson o con Moshi, esta ultima es la recomendada para retrofit
     */

    /*
    Moshi en vez de devolver un string, nos devolverá un objeto con la estructura del json de la api

     */

}