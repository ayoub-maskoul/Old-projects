package com.example.fragment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.holder, BlankFragment()).commit()

        var st = true



        var cr = CoroutineScope(
            Dispatchers.Default).launch {
            for (i in 0..1000){
                    delay(100);
                    if (st ){
                       withContext(Dispatchers.Main){
                           supportFragmentManager.beginTransaction().replace(R.id.holder, BlankFragment()).commit()
                       }
                        st = false
                    }else{
                        withContext(Dispatchers.Main) {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.holder, BlankFragment2()).commit()
                        }
                        st = true
                    }

                }
        }

        var btn = findViewById<Button>(R.id.add)
        var btn1 = findViewById<Button>(R.id.home)

        btn.setOnClickListener {
            cr.cancel()
            if(cr.isActive){

            }

        }

        btn1.setOnClickListener {
            cr.start()
            if(cr.isCancelled){

            }

        }









    }

}