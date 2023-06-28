package com.example.permission

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.efm205.R

class MainActivity : AppCompatActivity() {
    lateinit var btnentr: Button
    lateinit var btncall: Button
    lateinit var progr: TextView
    lateinit var phone: TextView
    lateinit var progressBar: ProgressBar
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnentr = findViewById(R.id.entry)
        btncall = findViewById(R.id.call)
        progr = findViewById(R.id.progress)
        phone = findViewById(R.id.num)
        btnentr.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, ListActivity::class.java)
            startActivity(intent)
        })
        progressBar = findViewById(R.id.progressBar)
        btncall.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                makePhoneCall()
                Toast.makeText(this@MainActivity, "Permission is already Allow", Toast.LENGTH_SHORT)
                    .show()
            } else {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), CALL_PHONE)
            }
        }
        Thread {
            for (i in 0..100) {
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    progressBar.progress = i
                    progr.text = "Progress...$i"
                }
            }
            runOnUiThread {
                btnentr.isEnabled = true
                progressBar.visibility = View.INVISIBLE
                progr.visibility = View.INVISIBLE
            }
        }.start()
    }

    private fun makePhoneCall() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + phone.text.toString())
        startActivity(intent)
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CALL_PHONE) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                makePhoneCall()
            } else {
                Toast.makeText(this@MainActivity, "Permission is Deny", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val CALL_PHONE = 0
    }
}