package com.pedrosaez.registro_superheroes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SuperHero(val superHeroName: String,  val alterEgo: String,val  bio: String, val power: Float):Parcelable
