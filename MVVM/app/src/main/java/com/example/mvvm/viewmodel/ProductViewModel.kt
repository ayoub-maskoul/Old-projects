package com.example.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.Product
import com.example.mvvm.repository.Rep

class ProductViewModel : ViewModel() {
    val rep: Rep = Rep()
    fun calculateTTCPrice(name: String, price: Double) {
        rep.calculateTTCPrice(name,price)
    }
    fun getResult(): LiveData<Product> {

        return rep.livedata
    }



}