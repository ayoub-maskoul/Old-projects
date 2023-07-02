package com.example.jobworkmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {

        Log.d("TAG", "Doing some work")




        return Result.retry()
    }
}