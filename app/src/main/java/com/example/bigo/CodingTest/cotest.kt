package com.example.bigo.CodingTest

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigo.MainPage.Navi
import com.example.bigo.coParcel
import com.example.bigo.databinding.P71CotestBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class cotest : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    val coName = FirebaseFirestore.getInstance()
    val itemList = arrayListOf<cotestData>()
    val adapter = cotestAdapter(itemList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P71CotestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val level = intent.getStringExtra("level")

        binding.recycleCotest.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleCotest.adapter = adapter

        coName.collection("cotest")
            .get().addOnSuccessListener { document ->
                itemList.clear()
                for (dc in document) {
                    val item = cotestData(dc["coID"] as? String?, dc["coName"] as? String?)
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }


        var coID = arrayListOf<coParcel>()
        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid.toString()
        database = Firebase.database.getReference("Users")
        adapter.setItemClickListener(object : cotestAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                coID.clear()
                coID.add(coParcel(itemList[position].step as String, itemList[position].coName as String ))
                updateCt(uid, itemList[position].step as String, itemList[position].coName as String )
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        when (snapshot.child(uid).child("level").value) {
                            "초급" -> {
                                val nextIntent = Intent(this@cotest, cotestQ_new::class.java)
                                nextIntent.putParcelableArrayListExtra("coID", coID)
                                startActivity(nextIntent)
                            }
                            "중급" -> {
                                val nextIntent = Intent(this@cotest, cotestQ_normal::class.java)
                                nextIntent.putParcelableArrayListExtra("coID", coID)
                                startActivity(nextIntent)
                            }
                            "고급" -> {
                                val nextIntent = Intent(this@cotest, cotestQ_pro::class.java)
                                nextIntent.putParcelableArrayListExtra("coID", coID)
                                startActivity(nextIntent)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@cotest, "fail data set", Toast.LENGTH_SHORT).show()
                    }
                })

            }
        })

        binding.back.setOnClickListener {
            val intent = Intent(this, Navi::class.java)
            startActivity(intent)
        }
    }

    private fun updateCt(uid: String, step: String, coName: String){
        database = FirebaseDatabase.getInstance().getReference("Users")

        val user = mapOf<String,String>(
            "coStep" to step,
            "coName" to coName
        )

        database.child(uid).updateChildren(user)
    }
}