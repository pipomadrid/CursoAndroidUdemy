package com.pedrosaez.earthquakemonitor.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

/*
Implementamos retrofit:
Creamos una interfaz con los request para traer los datos
es código muy repetitivo, se puede casi cortar y pegar
 */
interface EqApiService {
    @GET("all_hour.geojson") // aqui le estamos diciendo que obtenga los datos de la url base mas el valor introducido
    //  la web entera seria asi --> https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson
    //suspend fun getLastHourEarthquakes():String
    suspend fun getLastHourEarthquakes(): EqJsonResponse // para Moshi
}

// Creamos el servicio de retrofit usando la interfaz y la url con la que nos comunicaremos

var retrofit = Retrofit.Builder()
    .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/") // es la url base
    .addConverterFactory(MoshiConverterFactory.create()) // lo usamos para Moshi, tranforma el json en objeto
    //.addConverterFactory(ScalarsConverterFactory.create()) // esto convierte los datos en string al descargarlos
    .build()

var service :EqApiService= retrofit.create<EqApiService>(EqApiService::class.java)
