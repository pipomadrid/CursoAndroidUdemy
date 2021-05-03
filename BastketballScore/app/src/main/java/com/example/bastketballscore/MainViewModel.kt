package com.example.bastketballscore

import androidx.lifecycle.ViewModel

//No se peude pasar nada de views a esta clase , sólo logica
//los métodos y variables deben ser publicos para poder pasarlos al view
class MainViewModel:ViewModel() {

    var localScore = 0
    var visitorScore=0


    fun reset_scores() {
        localScore = 0
        visitorScore = 0
    }

    fun rest(isLocal:Boolean){

        if(isLocal && localScore > 0){
            localScore--
        }else if (visitorScore >0)
            visitorScore--
    }
    fun sum(puntos:Int,isLocal:Boolean) {
        if (isLocal) {
            localScore += puntos
        } else
            visitorScore += puntos
    }


}