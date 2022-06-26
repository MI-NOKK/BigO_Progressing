package com.example.bigo.myPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R

class ExpandableAdapter(
    private val NoticeList: List<myNotice>
) : RecyclerView.Adapter<ExpandableAdapter.MyViewHolder>() {

    class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind

                    (Notice: myNotice) {
            val txtName = itemView.findViewById<TextView>(R.id.txt_name)
            val imgMore = itemView.findViewById<ImageButton>(R.id.img_button)
            val layoutExpand = itemView.findViewById<LinearLayout>(R.id.layout_expand)
            val description = itemView.findViewById<TextView>(R.id.description)
            val today = itemView.findViewById<TextView>(R.id.today)

            txtName.text = Notice.notice
            today.text = Notice.today

            imgMore.setOnClickListener {
                val show = toggleLayout(!Notice.isExpanded, it, layoutExpand)
                Notice.isExpanded = show
                description.text = Notice.description
            }
        }

        private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout): Boolean {
            ToggleAnimation.toggleArrow(view, isExpanded)
            if (isExpanded) {
                ToggleAnimation.expand(layoutExpand)
            } else {
                ToggleAnimation.collapse(layoutExpand)
            }
            return isExpanded
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_p6_5_notice, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(NoticeList[position])
    }

    override fun getItemCount(): Int {
        return NoticeList.size
    }
}