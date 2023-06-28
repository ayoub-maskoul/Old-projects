package com.example.permission

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.permission.R

@SuppressLint("ValidFragment")
class detailFragment(var nom: String, var des: String) : Fragment() {
    lateinit var tvnom: TextView
    lateinit var tvdesc: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        tvnom = view.findViewById(R.id.nom)
        tvdesc = view.findViewById(R.id.desc)
        tvnom.text = nom
        tvdesc.text=des
        return view
    }
}