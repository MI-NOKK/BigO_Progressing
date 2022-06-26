package com.example.bigo.licence

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigo.databinding.P92LicenseListBinding
import com.example.bigo.lcParcel
import com.example.bigo.lcidParcel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class licenceList : AppCompatActivity() {

    val lcDate = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    val itemList = arrayListOf<licenceListData>()
    val adapter = licenceListAdapter(itemList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P92LicenseListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var nameList = intent.getParcelableArrayListExtra<lcParcel>("nameList")
        binding.licenseTitle.text = nameList?.get(0)?.lcName.toString()
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

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid.toString()
        var nextIntent = Intent(this, licenceQ::class.java)
        var lcidList = arrayListOf<lcidParcel>()
        adapter.setItemClickListener(object: licenceListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val dateId = itemList[position].dateId as String
                lcidList.clear()
                lcidList.add(lcidParcel(dateId, lcId))
                updateLc(uid, dateId, lcId)
                nextIntent.putParcelableArrayListExtra("lcidList", lcidList)
                startActivity(nextIntent)
            }
        })

        binding.back.setOnClickListener {
            val intent = Intent(this, licence::class.java)
            startActivity(intent)
        }
    }

    private fun updateLc(uid: String, dateId: String, lcId: String){
        database = FirebaseDatabase.getInstance().getReference("Users")

        val user = mapOf<String,String>(
            "dateId" to dateId,
            "lcId" to lcId
        )

        database.child(uid).updateChildren(user)
    }
}