package com.pedrosaez.pokedex

data class Pokemon(val id: Long, val name:String,val hp:Int, val attack:Int, val defense:Int,val speed:Int, val type:PokemonType) {

    enum class PokemonType{
        GRASS, FIRE, WATER, ELECTRIC
    }
}