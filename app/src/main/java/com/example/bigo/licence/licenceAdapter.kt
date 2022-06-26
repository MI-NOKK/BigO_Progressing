package com.example.bigo.licence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R


class licenceAdapter(val nameList: ArrayList<licenceData>) :
    RecyclerView.Adapter<licenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_p9_1_license, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = nameList[position].lcName //data class 변수 이름

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

        val name : TextView = view.findViewById(R.id.lcName)

        fun bind(item: licenceData) {
        }
    }
}