package com.example.bastketballscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.bastketballscore.ScoreActivity.Companion.LOCAL_SCORE_KEY
import com.example.bastketballscore.ScoreActivity.Companion.VISITOR_SCORE_KEY
import com.example.bastketballscore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //team1
        val bt_restar1_team1 = binding.btRest1
        val bt_sumar1_team1 = binding.btSum1Team1
        val bt_sumar2_team1 = binding.btSum2Team1
        val score_team1= binding.scoreTeam1

        //team2
        val bt_restar1_team2 = binding.btRest2
        val bt_sumar1_team2 = binding.btSum1Team2
        val bt_sumar2_team2 = binding.btSum2Team2
        val score_team2= binding.scoreTeam2

        //reset
        val reset = binding.restart

        //ir a detalles
        val detalles = binding.btDetails


        //operaciones equipo 1
        bt_sumar1_team1.setOnClickListener {
            sum(score_team1,1)
        }

        bt_sumar2_team1.setOnClickListener {
            sum(score_team1,2)
        }
        bt_restar1_team1.setOnClickListener {
            rest(score_team1)
        }

        //operaciones equipo2
        bt_sumar1_team2.setOnClickListener {
            sum(score_team2,1)
        }

        bt_sumar2_team2.setOnClickListener {
            sum(score_team2,2)
        }
        bt_restar1_team2.setOnClickListener {
            rest(score_team2)
        }


        //resetear
        reset.setOnClickListener {
          reset_scores(score_team1,score_team2)
        }

        //ir a detalles
        detalles.setOnClickListener {
            details(score_team1,score_team2)

        }
    }


    //Funcion sumar 1

    fun sum(scoreTeam:TextView,puntos:Int){
        val resultado = scoreTeam.text.toString().toInt() + puntos
        scoreTeam.text = resultado.toString()

    }
    //Funcion restar

    fun rest(scoreTeam:TextView){
        val puntos= scoreTeam.text.toString().toInt()
        if(puntos<=0){
            scoreTeam.text="0"
        }else
            scoreTeam.text=(puntos-1).toString()


    }
    //funcion reset marcadores

    fun reset_scores(scoreTeam: TextView,scoreTeam2: TextView){
        scoreTeam.text="0"
        scoreTeam2.text="0"

    }
    //funcion detalles

    fun details(scoreTeam: TextView,scoreTeam2: TextView){

        val scoreTeam1 = scoreTeam.text.toString()
        val scoreTeam2 = scoreTeam2.text.toString()
        val intent = Intent(this,ScoreActivity::class.java)
        intent.putExtra(LOCAL_SCORE_KEY,scoreTeam1)
        intent.putExtra(VISITOR_SCORE_KEY,scoreTeam2)
        startActivity(intent)
    }

}