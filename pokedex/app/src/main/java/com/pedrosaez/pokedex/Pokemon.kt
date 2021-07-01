package com.pedrosaez.pokedex

data class Pokemon(val id: Long, val name:String,val hp:Int, val attack:Int, val defense:Int,val speed:Int,
                   val type:PokemonType,val imageUrl:String, val sound :Int) {

    enum class PokemonType{
        GRASS, FIRE, WATER, ELECTRIC
    }
}