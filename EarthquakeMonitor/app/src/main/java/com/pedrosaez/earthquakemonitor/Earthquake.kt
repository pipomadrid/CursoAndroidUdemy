package com.pedrosaez.earthquakemonitor

import androidx.room.Entity
import androidx.room.PrimaryKey

// Para usar room debemos tener una data class que será la table y la clave primaria
// Para la operaciones usaremos una clase DAO -->  Data Access Object
@Entity(tableName = "earthquakes")
data class Earthquake(@PrimaryKey val id: String, val place: String, val magnitude: Double, val time: Long,
                      val longitude: Double, val Latitude: Double
)
