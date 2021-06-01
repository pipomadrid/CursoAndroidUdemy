package com.example.bastketballscore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//No se puede pasar nada de views a esta clase , sólo logica
//los métodos y variables deben ser publicos para poder pasarlos al view
class MainViewModel:ViewModel() {


    /*
    Los liveDate son varaibles que insertamos en el viewModel y que a su vez contienen otras
    variables
    Con liveData no podemos asignar valores
    Con MutableLiveDate si podemos asignarles valores
     */

    //lo hacemos privados para encapsularlos y pasamos el resultado a
    // livedata que no se pueden variar y estos si los dejamos publicos para poder usarlos en el Main

    private var _localScore:MutableLiveData<Int> = MutableLiveData()
    private var _visitorScore:MutableLiveData<Int> = MutableLiveData()

    val localScoreLiveData: LiveData<Int>
        get()=_localScore // no podemos asignar el valor pero si obtenerlos
    val visitorScoreLiveData: LiveData<Int>
        get()= _visitorScore

    init{ //se ejecuta automaticamente cuando se crea el viewmodel, establecemos valores a 0 para evitar nulos
        reset_scores()
    }

    fun reset_scores() {
        _localScore.value = 0 // para asignar valor a los mutableliveData se usa .value
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