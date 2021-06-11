package com.pedrosaez.earthquakemonitor.database

import androidx.room.*
import com.pedrosaez.earthquakemonitor.Earthquake

//Esta es la clase que tendrá las operaciones que usará room con la base de datos
@Dao
interface EqDao {

    //Con @Insert no hace falta que usemos SQL, ya le estamos dando la orden ,y le decimos que en caso
    // de conflicto con la clave al hacer un Insert replace el terremoto por el nuevo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(eqList: MutableList<Earthquake>)


    //Con @Query  introducimos una consulta que en este caso nos devolverá todos los terremotos
    @Query("SELECT * FROM earthquakes")
    fun getEarthQuakes() : MutableList<Earthquake>

    @Query("SELECT * FROM earthquakes WHERE magnitude > :magnitude") //magnitude se refiere a
    // la de la data class y :magnitude al del método  getEarthQuakesWithMagnitude
    fun getEarthQuakesWithMagnitude(magnitude:Double) : MutableList<Earthquake>


    @Update
    fun updateEq(vararg eq: Earthquake) //varagr significa argumento variables y nos permite pasar un objeto
    //o varios objetos

    @Delete
    fun deleteEq(vararg eq: Earthquake)

}