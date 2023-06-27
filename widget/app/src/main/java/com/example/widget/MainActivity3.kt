package com.example.widget

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cc204.R

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val activity = findViewById<ConstraintLayout>(R.id.acivity3)


        val nom = intent.getStringExtra("nom")
        val address = intent.getStringExtra("address")
        val tele = intent.getStringExtra("tele")
        val date = intent.getStringExtra("date")
        val peys = intent.getStringExtra("peys")
        Toast.makeText(this,nom,Toast.LENGTH_LONG).show()

        val resNom = findViewById<TextView>(R.id.nom_res)
        val resAddres = findViewById<TextView>(R.id.res_adres)
        val restele = findViewById<TextView>(R.id.res_tele)
        val resPeys = findViewById<TextView>(R.id.res_peys)
        val resdate = findViewById<TextView>(R.id.res_nais)

        val validation = findViewById<Button>(R.id.btn_valid)

        // show resulta
        validation.setOnClickListener {
            resNom.text = nom
            resAddres.text = address
            restele.text = tele
            resPeys.text = peys
            resdate.text = date
        }



        activity.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity3) {
            override fun onSwipeRight(): Boolean {
                val i = Intent(this@MainActivity3,MainActivity2::class.java )
                startActivity(i)
                return true
            }


        })
    }
}