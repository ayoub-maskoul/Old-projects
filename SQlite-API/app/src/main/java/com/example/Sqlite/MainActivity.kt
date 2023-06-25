package com.example.Sqlite

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var atapterTache:AtapterTache
    lateinit var recyclerView: RecyclerView
    lateinit var dbTache:DBTache
    lateinit var tacheList:ArrayList<Taches>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiBuilder = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/youssefyazidi/api3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = apiBuilder.create(JsonPlaceholderApi::class.java)

        val btn:Button=findViewById(R.id.Load_data)
        dbTache = DBTache(this@MainActivity)

        val db = dbTache.writableDatabase
        dbTache.deleteAllTaches(db)
        btn.setOnClickListener {
            api.getAllTache().enqueue( object : Callback<ArrayList<Taches>>{
                override fun onResponse(call: Call<ArrayList<Taches>>?, response: Response<ArrayList<Taches>>?) {

                    Log.d("TAG","not error"+response?.body().toString())
                    dbTache = DBTache(this@MainActivity)
                    val db = dbTache.writableDatabase
                    for (tache in response?.body()!!) {
                        dbTache.addTache(db, tache)
                    }


                    recyclerView=findViewById<RecyclerView>(R.id.rv)
                    tacheList = dbTache.getAllTache(db)

                    atapterTache= AtapterTache(tacheList,  object : AtapterTache.OptionsMenuClickListener{

                        override fun onOptionsMenuClicked(position: Int) {

                            val popupMenu = PopupMenu(this@MainActivity , recyclerView[position])
                            popupMenu.inflate(R.menu.options_menu)
                            popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
                                override fun onMenuItemClick(item: MenuItem?): Boolean {
                                    when(item?.itemId){
                                        R.id.delete -> {

                                            dbTache = DBTache(this@MainActivity)
                                            val db = dbTache.writableDatabase
                                            dbTache.DeleteTache(db,position)
                                            tacheList.removeAt(position)

                                            atapterTache.notifyDataSetChanged()


                                            return true
                                        }
                                        R.id.add -> {

                                            dbTache = DBTache(this@MainActivity)
                                            val db = dbTache.writableDatabase
                                            dbTache.addTache(db, Taches(10,"ddd","ddd","ssss"))

                                            atapterTache.notifyDataSetChanged()


                                            Toast.makeText(this@MainActivity , "Add" , Toast.LENGTH_SHORT).show()
                                            return true
                                        }

                                    }
                                    return false
                                }
                            })
                            popupMenu.show()

                        }
                    })
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter=atapterTache
                    atapterTache.notifyDataSetChanged()

                }

                override fun onFailure(call: Call<ArrayList<Taches>>?, t: Throwable?) {
                    Log.d("TAG","error"+t?.message.toString())

                }
            })
        }
    }
    fun performOptionsMenuClick(position:Int){

    }
}