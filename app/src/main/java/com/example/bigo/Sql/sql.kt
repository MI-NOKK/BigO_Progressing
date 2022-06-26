package com.example.bigo.Sql

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigo.MainPage.Navi
import com.example.bigo.databinding.P81SqlBinding
import com.example.bigo.sqlParcel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class sql : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    val sqlName = FirebaseFirestore.getInstance()
    val itemList = arrayListOf<sqlData>()
    val adapter = sqlAdapter(itemList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P81SqlBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val level = intent.getStringExtra("level")

        binding.recycleSqltest.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleSqltest.adapter = adapter

        sqlName.collection("sql")
            .get().addOnSuccessListener { document ->
                itemList.clear()
                for (dc in document) {
                    val item = sqlData(dc["sqlID"] as? String?, dc["sqlName"] as? String?)
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        var sqlID = arrayListOf<sqlParcel>()
        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid.toString()
        database = Firebase.database.getReference("Users")
        adapter.setItemClickListener(object: sqlAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                sqlID.clear()
                sqlID.add(sqlParcel( itemList[position].step as String, itemList[position].sqlName as String))
                updateSQL(uid, itemList[position].step as String, itemList[position].sqlName as String)
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        when (snapshot.child(uid).child("level").value) {
                            "초급" -> {
                                val nextIntent = Intent(this@sql, sqlQ_new::class.java)
                                nextIntent.putParcelableArrayListExtra("sqlID", sqlID)
                                startActivity(nextIntent)
                            }
                            "중급" -> {
                                val nextIntent = Intent(this@sql, sqlQ_normal::class.java)
                                nextIntent.putParcelableArrayListExtra("sqlID", sqlID)
                                startActivity(nextIntent)
                            }
                            "고급" -> {
                                val nextIntent = Intent(this@sql, sqlQ_pro::class.java)
                                nextIntent.putParcelableArrayListExtra("sqlID", sqlID)
                                startActivity(nextIntent)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@sql, "fail data set", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })

        binding.back.setOnClickListener {
            val intent = Intent(this, Navi::class.java)
            startActivity(intent)
        }
    }

    private fun updateSQL(uid: String, step: String, sqlName: String){
        database = FirebaseDatabase.getInstance().getReference("Users")

        val user = mapOf<String,String>(
            "sqlStep" to step,
            "sqlName" to sqlName
        )

        database.child(uid).updateChildren(user)
    }
}