package com.example.mvvm.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.Product

interface IRep {
    fun calculateTTCPrice(name:String, price:Double): MutableLiveData<Product>
}