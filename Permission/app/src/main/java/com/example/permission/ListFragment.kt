package com.example.permission

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.efm205.R

class ListFragment : Fragment() {
    lateinit var listView: ListView
    lateinit var list: PaysAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val pays1 = Pays()
        val pays2 = Pays()
        val pays3 = Pays()
        val pays4 = Pays()
        val pays5 = Pays()
        listView = view.findViewById<View>(R.id.list) as ListView
        pays1.nom = "Morocco"
        pays1.capitale = "rebat"
        pays1.desc = getString(R.string.desc_moro)
        pays2.nom = "Libye"
        pays2.capitale = "Tripoli"
        pays2.desc = getString(R.string.desc_lib)
        pays3.nom = "Algérie"
        pays3.capitale = "Algérie"
        pays3.desc = getString(R.string.desc_algr)
        pays4.nom = "Mauritanie"
        pays4.capitale = "Nouakchott"
        pays4.desc = getString(R.string.desc_murti)
        pays5.nom = "Tunisie"
        pays5.capitale = "Tunisie"
        pays5.desc = getString(R.string.desc_tuni)
        pays1.drapeau = R.drawable.maroc
        pays2.drapeau = R.drawable.libye
        pays3.drapeau = R.drawable.algerie
        pays4.drapeau = R.drawable.moritanie
        pays5.drapeau = R.drawable.tunisie
        val list1: ArrayList<Pays> = ArrayList()
        list1.add(pays1)
        list1.add(pays2)
        list1.add(pays3)
        list1.add(pays4)
        list1.add(pays5)
        list = PaysAdapter(activity, 0, list1)
        listView.adapter = list
        return view
    }
}