package com.example.permission

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.efm205.R

class PaysAdapter(private val ctx: Context, resource: Int, private val listePays: ArrayList<Pays>) :
    ArrayAdapter<Pays?>(
        ctx, resource, listePays as List<Pays?>
    ) {
    private var inflater: LayoutInflater? = null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val p = listePays[position]
        if (convertView == null) {
            inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater!!.inflate(R.layout.list, parent, false)
        }
        val tvnom = convertView!!.findViewById<TextView>(R.id.txtvnom)
        val tvCap = convertView.findViewById<TextView>(R.id.txtvCapitale)
        val img = convertView.findViewById<ImageView>(R.id.image)
        tvnom.text = p.nom
        tvCap.text = p.capitale
        img.setImageResource(p.drapeau)
        convertView.setOnClickListener { view ->
            val activity = view.context as AppCompatActivity
            if (activity.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                activity.fragmentManager.beginTransaction()
                    .replace(R.id.framedet, detailFragment(p.nom!!, p.desc!!))
                    .addToBackStack(null).commit()
            } else {
                activity.fragmentManager.beginTransaction()
                    .replace(R.id.frame, detailFragment(p.nom!!, p.desc!!))
                    .addToBackStack(null).commit()
            }
        }
        return convertView
    }

    override fun getCount(): Int {
        return listePays.size

    }
}