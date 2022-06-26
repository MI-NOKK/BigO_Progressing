package com.example.bigo.MainPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R

class lastViewAdapter(lastList: ArrayList<Int>):RecyclerView.Adapter<lastViewAdapter.PagerViewHolder>() {
    var item = lastList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.last.setImageResource(item[position])

        holder.itemView.setOnClickListener {
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
    inner class PagerViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.last_list_item, parent, false)){
        val last = itemView.findViewById<ImageView>(R.id.ivLast)
    }
}