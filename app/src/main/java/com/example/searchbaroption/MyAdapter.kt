package com.example.searchbaroption

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class MyAdapter(var list: ArrayList<MyModal>,val context:Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val v = LayoutInflater.from(p0.context).inflate(R.layout.layout, p0, false)
        return MyViewHolder(v, list,context)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
      //   p0.item(list[p1])

        val m: MyModal = list.get(p1)


        p0.textViewName.text = m.name

        p0.textViewName.setOnClickListener{

          //  Toast.makeText(context, ""+p0.textViewName.text, Toast.LENGTH_SHORT).show()
            val intent = Intent("custom-message")
            intent.putExtra("path", p0.textViewName.text.toString())
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }

    }
    fun filterList(filterdNames:ArrayList<MyModal> ) {
        list = filterdNames
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View, list: ArrayList<MyModal>,val context:Context) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

       val textViewName: TextView = itemView.findViewById(R.id.name)
        val list1 = list




        /*  fun item(model:MyModal) {
              val textViewName = itemView.findViewById(R.id.name) as TextView
              textViewName.text = model.name
              textViewName.setOnClickListener(this)
              itemView.setOnClickListener(this)

          }*/
        override fun onClick(v: View?) {

         /*   var pos:Int=adapterPosition
            val m: MyModal = list1.get(pos)

            Toast.makeText(context, ""+m.name, Toast.LENGTH_SHORT).show()*/
        }


    }

}