package com.example.bigo.myPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R


class CheckCtAdapter(val nameList: ArrayList<Check>) :
    RecyclerView.Adapter<CheckCtAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_check_cotest, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.step.text = (position + 1).toString()
        viewHolder.name.text = nameList[position].name

        viewHolder.itemView.setOnClickListener {
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
        val name : TextView = view.findViewById(R.id.coName)


        fun bind(item: Check) {
        }
    }
}