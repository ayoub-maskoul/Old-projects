package com.example.permission

import android.app.Fragment
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.efm205.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            loadfrgment(ListFragment())
            detfrgment(detailFragment("", ""))
        } else {
            loadfrgment(ListFragment())
        }
    }

    private fun detfrgment(fragment: Fragment) {
        val fm = fragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.framedet, fragment)
        ft.commit()
    }

    private fun loadfrgment(fragment: Fragment) {
        val fm = fragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.frame, fragment)
        ft.commit()
    }
}