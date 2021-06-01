package com.example.bastketballscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bastketballscore.ScoreActivity.Companion.LOCAL_SCORE_KEY
import com.example.bastketballscore.ScoreActivity.Companion.VISITOR_SCORE_KEY
import com.example.bastketballscore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private var localScore = 0 //las pasamos al viewmodel para separar la lógica de las vistas
//    private var visitorScore=0

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainViewModel // Variable qu vamos a usar para crear el viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //creamos el viewmodel  y la clase MainViewModel
        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)

        // ponemos quien va  a ser el observador y el observador como argumentos
        // Cuando observe que localscore cambió  va a llamar a la función lambda y este va a traer
        // el valor de localscore
        viewModel.localScoreLiveData.observe(this, Observer {
            localScoreValue:Int -> // no trae el mutableLiveDate si no el valor Int que corresponde al mutableLiveData
            binding.scoreTeam1.text = localScoreValue.toString() //asignamos el valor obtenido al textview correspondiente

        })
        viewModel.visitorScoreLiveData.observe(this, Observer {
            visitorScoreValue:Int ->
            binding.scoreTeam2.text = visitorScoreValue.toString()

        })
        //team1
        val bt_restar1_team1 = binding.btRest1
        val bt_sumar1_team1 = binding.btSum1Team1
        val bt_sumar2_team1 = binding.btSum2Team1
//        val score_team1= binding.scoreTeam1

        //team2
        val bt_restar1_team2 = binding.btRest2
        val bt_sumar1_team2 = binding.btSum1Team2
        val bt_sumar2_team2 = binding.btSum2Team2
//        val score_team2= binding.scoreTeam2

        //reset
        val reset = binding.restart

        //ir a detalles
        val detalles = binding.btDetails


        //operaciones equipo 1
        bt_sumar1_team1.setOnClickListener {
            sum(1,isLocal = true)
        }

        bt_sumar2_team1.setOnClickListener {
            sum(2,isLocal = true)
        }
        bt_restar1_team1.setOnClickListener {
            rest(isLocal = true)
        }

        //operaciones equipo2
        bt_sumar1_team2.setOnClickListener {
            sum(1  ,isLocal = false)
        }

        bt_sumar2_team2.setOnClickListener {
            sum(2,isLocal = false)
        }
        bt_restar1_team2.setOnClickListener {
            rest(isLocal = false)
        }


        //resetear
        reset.setOnClickListener {
          reset_scores()
        }

        //ir a detalles
        detalles.setOnClickListener {
            details()

        }
    }

    //Funcion sumar

    fun sum(puntos:Int, isLocal:Boolean){
        viewModel.sum(puntos,isLocal) // con viewmodel usamos en método que está en la clase MainViewModel
        /*if(isLocal){
            binding.scoreTeam1.text= viewModel.localScore.toString()

        }else
            binding.scoreTeam2.text= viewModel.visitorScore.toString()
//        val resultado = scoreTeam.text.toString().toInt() + puntos
//        scoreTeam.text = resultado.toString()*/

    }
    //Funcion restar

    fun rest(isLocal:Boolean){
        viewModel.rest(isLocal)
       /* if(isLocal ){
            binding.scoreTeam1.text= viewModel.localScore.toString()
        }else
            binding.scoreTeam2.text= viewModel.visitorScore.toString()*/

    }
    //funcion reset marcadores

    fun reset_scores(){
//        localScore = 0 // pasadas a viewmodel
//        visitorScore=0
       viewModel.reset_scores()
        //binding.scoreTeam1.text= viewModel.localScore.toString()
       // binding.scoreTeam2.text= viewModel.visitorScore.toString()


    }
    //funcion detalles

    fun details(){
        val intent = Intent(this,ScoreActivity::class.java)
        intent.putExtra(LOCAL_SCORE_KEY,viewModel.localScoreLiveData.value)
        intent.putExtra(VISITOR_SCORE_KEY,viewModel.visitorScoreLiveData.value)
        startActivity(intent)
    }

}