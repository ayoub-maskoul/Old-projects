package com.example.jobworkmanager

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobService:JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {

        Log.d("TAG", "Job started")


        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("TAG", "Job stopped")

        return false
    }
}