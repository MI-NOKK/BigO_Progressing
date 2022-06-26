package com.example.bigo.licence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R


class licenceListAdapter(val nameList: ArrayList<licenceListData>) :
    RecyclerView.Adapter<licenceListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_p9_2_license_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.date.text = nameList[position].date

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

        val date : TextView = view.findViewById(R.id.lcDate)

        fun bind(item: licenceListData) {
        }
    }
}