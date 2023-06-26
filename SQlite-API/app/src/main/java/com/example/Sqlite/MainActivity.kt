package com.example.Sqlite

import android.os.AsyncTask
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
import com.example.cc205.R
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


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
                                            dbTache.deleteTache(db,position)
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

//here use ASyncTask
   /* inner class MatacheAsyc : AsyncTask<String?, Int?, String?>() {

        override fun doInBackground(vararg params: String?): String? {
            var data: String? = ""
            try {
                val url = URL("https://my-json-server.typicode.com/youssefyazidi/api3/taches")
                val con: HttpsURLConnection = url.openConnection() as HttpsURLConnection
                val str: InputStream = con.inputStream
                val red = BufferedReader(InputStreamReader(str))
                var ligne = ""
                while (ligne != null) {
                    ligne = red.readLine()
                    data += ligne
                }
            } catch (er: Exception) {
                er.printStackTrace()
                Log.e("E rreur ", er.toString())
            }
            return data
        }

        override fun onPreExecute() {


        }

        override fun onPostExecute(s: String?) {


            val ta: ArrayList<Taches> = ArrayList()
            try {
                val array = JSONArray(s)
                for (i in 0 until array.length()) {
                    val postjson: JSONObject = array.getJSONObject(i)
                    val titre: String = postjson.getString("title")
                    val id: Int = postjson.getInt("id")
                    val date: String = postjson.getString("date")
                    val status: String = postjson.getString("status")
                    val t = Taches(id,titre,date,status)

                    ta.add(t)
                }

            } catch (er: Exception) {
                Log.e("Erreur ", er.toString())
            }
        }
    }*/

}