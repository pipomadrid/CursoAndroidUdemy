package com.pedrosaez.earthquakemonitor

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


interface EqApiService {
    @GET("users/{user}/repos")
    suspend fun getLastHourEarthquakes():String
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

var service :EqApiService= retrofit.create<EqApiService>(EqApiService::class.java)
