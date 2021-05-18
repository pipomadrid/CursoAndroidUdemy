package com.example.bastketballscore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//No se puede pasar nada de views a esta clase , sólo logica
//los métodos y variables deben ser publicos para poder pasarlos al view
class MainViewModel:ViewModel() {

    private var _localScore:MutableLiveData<Int> = MutableLiveData()
    private var _visitorScore:MutableLiveData<Int> = MutableLiveData()

    val localScore: LiveData<Int>
        get()=_localScore
    val visitorScore: LiveData<Int>
        get()= _visitorScore

    init{
        reset_scores()
    }

    fun reset_scores() {
        _localScore.value = 0
        _visitorScore.value = 0
    }

    fun rest(isLocal:Boolean){

        if(isLocal && _localScore.value!! > 0){
            _localScore.value = _localScore.value!! -1
        }else if (_visitorScore.value!! >0)
            _visitorScore.value = _visitorScore.value!!-1
    }
    fun sum(puntos:Int,isLocal:Boolean) {
        if (isLocal) {
            _localScore.value = _localScore.value!! + puntos
        } else
            _visitorScore.value = _visitorScore.value?.plus(puntos) // se puede hacer la suma de esta forma tb
    }


}