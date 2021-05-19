package com.pedrosaez.earthquakemonitor

data class Earthquake(val id: String, val place: String, val magnitude: Double, val time: Long,
    val longitude: Double, val Latitude: Double
) {
}