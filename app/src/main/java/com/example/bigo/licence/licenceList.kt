package com.example.bigo.licence

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigo.databinding.ActivityP91GichulListBinding
import com.example.bigo.lcParcel
import com.example.bigo.lcidParcel
import com.google.firebase.firestore.FirebaseFirestore

class licenceList : AppCompatActivity() {

    val lcDate = FirebaseFirestore.getInstance()
    val itemList = arrayListOf<licenceListData>()
    val adapter = licenceListAdapter(itemList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP91GichulListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var nameList = intent.getParcelableArrayListExtra<lcParcel>("nameList")
        binding.lcName.text = nameList?.get(0)?.lcName.toString()
        val lcId = nameList?.get(0)?.lcId.toString()

        binding.recycleDate.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleDate.adapter = adapter

        lcDate.collection("licence").document(lcId)
            .collection("Date")
            .get().addOnSuccessListener { document ->
                itemList.clear()
                for (dc in document) {
                    val item = licenceListData(dc["date"] as? String?, dc["dateID"] as? String?)
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        var nextIntent = Intent(this, licenceQ::class.java)
        var lcidList = arrayListOf<lcidParcel>()
        adapter.setItemClickListener(object: licenceListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                lcidList.clear()
                lcidList.add(lcidParcel(itemList[position].dateId as String, lcId))
                nextIntent.putParcelableArrayListExtra("lcidList", lcidList)
                startActivity(nextIntent)
            }
        })

        binding.back.setOnClickListener {
            val intent = Intent(this, licence::class.java)
            startActivity(intent)
        }
    }
}