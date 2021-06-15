package com.pedrosaez.earthquakemonitor

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
// Para usar room debemos tener una data class que será la table y la clave primaria
// Para la operaciones usaremos una clase DAO -->  Data Access Object
@Parcelize
@Entity(tableName = "earthquakes")
data class Earthquake(@PrimaryKey val id: String, val place: String, val magnitude: Double, val time: Long,
                      val longitude: Double, val latitude: Double):Parcelable
