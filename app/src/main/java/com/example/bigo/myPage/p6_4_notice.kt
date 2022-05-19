package com.example.bigo.myPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R

class p6_4_notice : AppCompatActivity() {

    private lateinit var personList: List<Notice>
    private lateinit var adapter: ExpandableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p6_4_notice)

        val recyclerView = findViewById<RecyclerView>(R.id.p6_4_notice)

        personList = ArrayList()
        personList = loadData()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpandableAdapter(personList)
        recyclerView.adapter = adapter

    }

    private fun loadData(): List<Notice> {
        val people = ArrayList<Notice>()

        val notice_names = resources.getStringArray(R.array.notice_name)

        for (i in notice_names.indices) {
            val person = Notice().apply {
                notice = notice_names[i]
            }
            people.add(person)
        }
        return people
    }
}