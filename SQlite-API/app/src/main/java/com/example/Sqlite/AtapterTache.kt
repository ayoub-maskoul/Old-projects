package com.example.Sqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cc205.R

class AtapterTache(val list:ArrayList<Taches>,
                   private var optionsMenuClickListener: OptionsMenuClickListener):
    RecyclerView.Adapter<AtapterTache.ViewHolderTache>() {
     class ViewHolderTache(val view:View):RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.titre)
        val status = view.findViewById<ImageView>(R.id.status)

    }
    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTache {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolderTache(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderTache, position: Int) {

        val item = list[position]
        holder.title.text = item.titre
        holder.view.setOnClickListener {
            optionsMenuClickListener.onOptionsMenuClicked(position)
        }
        if (item.status=="terminé"){

            holder.status.setImageResource(R.drawable.termine)
        }else{

            holder.status.setImageResource(R.drawable.pending)
        }
    }
}