package com.pedrosaez.earthquakemonitor.api


//Clase creada para parsear con Moshi
class Geometry(val coordinates: Array<Double>) {

    val longitud:Double
        get()=coordinates[0]
    val latitude:Double
        get() = coordinates[1]

}