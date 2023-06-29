package com.example.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {

    
    private lateinit var productViewModel: ProductViewModel
    lateinit var textViewResulat: TextView
    lateinit var editetextProductName: EditText
    lateinit var editetextProductPrice: EditText
    lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResulat = findViewById(R.id.resulta)
        editetextProductName = findViewById(R.id.editTextname)
        editetextProductPrice = findViewById(R.id.editTextPrice)
        buttonCalculate = findViewById(R.id.button)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        productViewModel.getResult().observe(this, Observer {
            product->
            textViewResulat.text=product.toString()
        })

        buttonCalculate.setOnClickListener {
            val name = editetextProductName.text.toString()
            val price = editetextProductPrice.text.toString().toDouble()
            productViewModel.calculateTTCPrice(name, price)

        }
    }
}