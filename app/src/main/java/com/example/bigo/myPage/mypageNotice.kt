package com.example.bigo.myPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.R

class mypageNotice : AppCompatActivity() {

    private lateinit var noticeList: List<myNotice>
    private lateinit var adapter: ExpandableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p6_5_notice)

        val recyclerView = findViewById<RecyclerView>(R.id.p6_4_notice)

        noticeList = ArrayList()
        noticeList = loadData()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpandableAdapter(noticeList)
        recyclerView.adapter = adapter

    }

    private fun loadData(): List<myNotice> {
        val content = ArrayList<myNotice>()

        val notice_names = resources.getStringArray(R.array.notice_name)
        val notice_cotent = resources.getStringArray(R.array.notice_cotent)
        val notice_today = resources.getStringArray(R.array.notice_today)

        for (i in notice_names.indices) {
            val notice_ct = myNotice().apply {
                notice = notice_names[i]
                description = notice_cotent[i]
                today = notice_today[i]
            }
            content.add(notice_ct)
        }

        return content
    }
}