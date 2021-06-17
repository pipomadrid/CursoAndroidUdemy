package com.pedrosaez.earthquakemonitor.api

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pedrosaez.earthquakemonitor.database.getDatabase
import com.pedrosaez.earthquakemonitor.main.MainRepository

class SyncWorkManager (appContext: Context, params: WorkerParameters): CoroutineWorker(appContext,params){
    companion object {
        const val WORK_NAME = "SyncWorkManager"
    }
    private val database = getDatabase(appContext)
    private var repository = MainRepository(database)

    override suspend fun doWork(): Result {
        repository.fetchEarthquakes(true)

        return Result.success()
    }


}