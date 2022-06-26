package com.example.bigo.Sql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R


class sqlAdapter(val nameList: ArrayList<sqlData>) :
    RecyclerView.Adapter<sqlAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_p8_1_sql, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.step.text = nameList[position].step
        viewHolder.name.text = nameList[position].sqlName //data class 변수 이름

        viewHolder.go.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val step : TextView = view.findViewById(R.id.step)
        val name : TextView = view.findViewById(R.id.sqlName)
        val go : Button = view.findViewById(R.id.Go)

        fun bind(item: sqlData) {
        }
    }
}