package com.example.bigo.licence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R


class licenceQAdapter(val nameList: ArrayList<licenceQData>) :
    RecyclerView.Adapter<licenceQAdapter.ViewHolder>() {

    //private var answer =  Array<String>(20,{""})
    private var answer =  Array<String>(20,{""})

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_p9_3_license_question, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = nameList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.Q.text = nameList[position].question
        viewHolder.ex1.text = nameList[position].example01
        viewHolder.ex2.text = nameList[position].example02
        viewHolder.ex3.text = nameList[position].example03
        viewHolder.ex4.text = nameList[position].example04

        viewHolder.ex.setOnCheckedChangeListener(null)
        viewHolder.ex.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.example01 -> answer[position] = "1"
                R.id.example02 -> answer[position] = "2"
                R.id.example03 -> answer[position] = "3"
                R.id.example04 -> answer[position] = "4"
            }
        }

    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun getAnswer() : Array<String>{
        return answer
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val Q: TextView = view.findViewById(R.id.Question)
        var ex: RadioGroup = view.findViewById(R.id.example)
        val ex1: RadioButton = view.findViewById(R.id.example01)
        val ex2: RadioButton = view.findViewById(R.id.example02)
        val ex3: RadioButton = view.findViewById(R.id.example03)
        val ex4: RadioButton = view.findViewById(R.id.example04)

        fun bind(item: licenceListData) {
        }
    }

}