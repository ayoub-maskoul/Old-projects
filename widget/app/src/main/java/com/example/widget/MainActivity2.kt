package com.example.widget

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cc204.R
import java.util.Calendar

class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val activity = findViewById<ConstraintLayout>(R.id.acivity2)

        val peys = findViewById<Spinner>(R.id.peyys)
        val intent = intent

        val nom =  intent.getStringExtra("nom")


        val address = intent.getStringExtra("address")
        val tele = intent.getStringExtra("tele")

        Toast.makeText(this,address.toString(), Toast.LENGTH_LONG).show()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val bn = findViewById<Button>(R.id.btn_date)
        val date = findViewById<TextView>(R.id.ress)
        var res = ""
        bn.setOnClickListener {
            // show date and selected date
            val dpd = DatePickerDialog(this, { _, myear,
                                               monthOfYear, dayOfMonth ->
                date.text= "$dayOfMonth/$monthOfYear/$myear"
                res = "$dayOfMonth/$monthOfYear/$myear"
            }, year, month, day)
            dpd.show()
        }
        var selectedPeys = ""



        ArrayAdapter.createFromResource(
            this,
            R.array.country,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            peys.adapter = adapter
        }
        peys.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedPeys = parent.getItemAtPosition(position) as String
                Toast.makeText(applicationContext, "Selected: $selectedPeys", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }


        activity.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity2) {
            override fun onSwipeRight(): Boolean {
                val i = Intent(this@MainActivity2,MainActivity::class.java )
                startActivity(i)
                return true
            }
            override fun onSwipeLeft(): Boolean {
                val i = Intent(this@MainActivity2,MainActivity3::class.java )
                i.putExtra("nom",nom)
                i.putExtra("address",address)
                i.putExtra("tele",tele.toString())
                i.putExtra("peys",selectedPeys)
                i.putExtra("date",res)

                startActivity(i)
                return true
            }


        })
    }
}