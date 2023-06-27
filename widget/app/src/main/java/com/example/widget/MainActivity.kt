package com.example.widget

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cc204.R

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val activity = findViewById<ConstraintLayout>(R.id.activity1)


        activity.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft(): Boolean {
                val i = Intent(this@MainActivity,MainActivity2::class.java )

                val nom = findViewById<EditText>(R.id.ed_noms)
                val address = findViewById<EditText>(R.id.edi_addresss)
                val telephone = findViewById<EditText>(R.id.ed_telephon)
                i.putExtra("nom",nom.text.toString())
                i.putExtra("tele",telephone.text.toString())
                i.putExtra("address",address.text.toString())
                startActivity(i)
                return true
            }


    })
    }

}