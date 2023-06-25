package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        val btn:Button = view.findViewById(R.id.button)

        btn.setOnClickListener {
            val edit:EditText = view.findViewById(R.id.editTextText)

            val data = edit.text.toString()

            val bundle = Bundle()

            // sent data to another fragment From fragment
            bundle.putString("data",data)

            val fragment2 = Fragment2()
            fragment2.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.holder, Fragment2())?.commit()

        }

        return view
    }


}