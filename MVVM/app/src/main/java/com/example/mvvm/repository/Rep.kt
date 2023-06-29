package com.example.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.Product
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Rep : IRep{

    private var rate:Double=1.1
    private val _livedata=MutableLiveData<Product>()
    val livedata: LiveData<Product>
        get() = _livedata

    @OptIn(DelicateCoroutinesApi::class)
    override fun calculateTTCPrice(
        name: String,
        price: Double
    ): MutableLiveData<Product> {
        var ttcprice=price*rate
        var p=Product(name,ttcprice)
        _livedata.postValue(p)
        GlobalScope.launch {
            rate = 1.2
            delay(5000L)
            ttcprice=price*rate
            p=Product(name,ttcprice)
            _livedata.postValue(p)
        }

        return _livedata
    }
}