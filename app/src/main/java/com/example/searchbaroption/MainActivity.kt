package com.example.searchbaroption

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var search: SearchView
    lateinit var toolbar: Toolbar
    lateinit var rv: RecyclerView
   lateinit var adapter: MyAdapter
    lateinit var arrayList: ArrayList<MyModal>
    var iname:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        search = findViewById(R.id.search)
        rv = findViewById(R.id.recyclerview)
        rv.layoutManager = LinearLayoutManager(this)
        rv.visibility=GONE
        setSupportActionBar(toolbar)


        var daysOfWeek = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        arrayList = ArrayList()

        daysOfWeek.forEach {
            arrayList.add(MyModal(it))
        }

adapter= MyAdapter(arrayList,this)
        rv.adapter =adapter

        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,
            IntentFilter("custom-message"))
       // Toast.makeText(this@MainActivity, "ji" + iname, Toast.LENGTH_LONG).show()
//        search.setQuery(iname,true)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {

                if(search.query.isEmpty()) rv.visibility=GONE
                else rv.visibility= VISIBLE



                //  Toast.makeText(this@MainActivity, "" + p0, Toast.LENGTH_SHORT).show()
                filter(p0.toString())
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                //  Toast.makeText(this@MainActivity, ""+p0, Toast.LENGTH_SHORT).show()
                return false
            }
        })

    }

    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<MyModal>()

        //looping through existing elements
        for (s in arrayList) {
            //if the existing elements contains the search input
            if (s.name.toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s)
            }
        }

        adapter.filterList(filterdNames)




    }

    val mBroadcastReceiver=object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {



                 iname=intent!!.getStringExtra("path")
            search.setQuery(iname,true)
            rv.visibility=GONE
          //  Toast.makeText(this@MainActivity, "ji" + iname, Toast.LENGTH_LONG).show()




                }


    }

    }


