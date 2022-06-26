package com.example.bigo.licence

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigo.MainPage.Navi
import com.example.bigo.databinding.P91LicenseBinding
import com.example.bigo.lcParcel
import com.google.firebase.firestore.FirebaseFirestore

class licence : AppCompatActivity() {

    val lcName = FirebaseFirestore.getInstance()
    val itemList = arrayListOf<licenceData>()
    val adapter = licenceAdapter(itemList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P91LicenseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.recycleLicense.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleLicense.adapter = adapter
        lcName.collection("licence")
            .get().addOnSuccessListener { document ->
                itemList.clear()
                for (dc in document) {
                    val item = licenceData(dc["lcName"] as String, dc["id"] as String)
                    itemList.add(item)
                   }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        var nextIntent = Intent(this, licenceList::class.java)
        var nameList = arrayListOf<lcParcel>()
        adapter.setItemClickListener(object: licenceAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                nameList.clear()
                nameList.add(lcParcel(itemList[position].lcName as String, itemList[position].lcId as String))
                nextIntent.putParcelableArrayListExtra("nameList", nameList)
                startActivity(nextIntent)
            }
        })

        binding.back.setOnClickListener {
            val intent = Intent(this, Navi::class.java)
            startActivity(intent)
        }
    }
}



