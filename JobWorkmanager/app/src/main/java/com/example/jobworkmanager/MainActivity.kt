package com.example.jobworkmanager

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val componentName = ComponentName(this,MyJobService::class.java)
        val jobInfo = JobInfo.Builder(101,componentName)
            .setRequiresCharging(true)
            .setPeriodic(6000)
            .setPersisted(true)
            .build()

        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)

        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}