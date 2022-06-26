package com.example.bigo.myPage

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigo.CodingTest.*
import com.example.bigo.Sql.*
import com.example.bigo.coParcel
import com.example.bigo.databinding.P62CheckBinding
import com.example.bigo.sqlParcel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class CheckQ : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    val db = FirebaseFirestore.getInstance()
    val itemCt = arrayListOf<Check>()
    val itemSql = arrayListOf<Check>()
    val adapterCt = CheckCtAdapter(itemCt)
    val adapterSql = CheckSqlAdapter(itemSql)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P62CheckBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recycleCheckCote.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleCheckCote.adapter = adapterCt

        binding.recycleCheckSql.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleCheckSql.adapter = adapterSql


        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid.toString()

        val dataCt = db.collection("check").document(uid).collection("cotest")
        val dataSql = db.collection("check").document(uid).collection("sql")

        dataCt.get().addOnSuccessListener { document ->
            itemCt.clear()
            for (dc in document) {
                val item = Check(dc["coId"] as? String?, dc["coName"] as? String?)
                itemCt.add(item)
            }
            adapterCt.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }

        dataSql.get().addOnSuccessListener { document ->
            itemSql.clear()
            for (dc in document) {
                val item = Check(dc["sqlId"] as? String?, dc["sqlName"] as? String?)
                itemSql.add(item)
            }
            adapterSql.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }

        var coID = arrayListOf<coParcel>()
        var sqlID = arrayListOf<sqlParcel>()
        firebaseAuth = FirebaseAuth.getInstance()
        database = Firebase.database.getReference("Users")

        adapterCt.setItemClickListener(object : CheckCtAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                coID.clear()
                coID.add(coParcel(itemCt[position].id as String, itemCt[position].name as String ))
                updateCt(uid, itemCt[position].id as String, itemCt[position].name as String)
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        when (snapshot.child(uid).child("level").value) {
                            "초급" -> {
                                val nextIntent = Intent(this@CheckQ, cotestQ_new::class.java)
                                nextIntent.putParcelableArrayListExtra("coID", coID)
                                startActivity(nextIntent)
                            }
                            "중급" -> {
                                val nextIntent = Intent(this@CheckQ, cotestQ_normal::class.java)
                                nextIntent.putParcelableArrayListExtra("coID", coID)
                                startActivity(nextIntent)
                            }
                            "고급" -> {
                                val nextIntent = Intent(this@CheckQ, cotestQ_pro::class.java)
                                nextIntent.putParcelableArrayListExtra("coID", coID)
                                startActivity(nextIntent)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@CheckQ, "fail data set", Toast.LENGTH_SHORT).show()
                    }
                })

            }
        })

        adapterSql.setItemClickListener(object : CheckSqlAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                sqlID.clear()
                sqlID.add(sqlParcel(itemSql[position].id as String, itemSql[position].name as String ))
                updateSQL(uid, itemSql[position].id as String, itemSql[position].name as String)
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        when (snapshot.child(uid).child("level").value) {
                            "초급" -> {
                                val nextIntent = Intent(this@CheckQ, sqlQ_new::class.java)
                                nextIntent.putParcelableArrayListExtra("sqlID", sqlID)
                                startActivity(nextIntent)
                            }
                            "중급" -> {
                                val nextIntent = Intent(this@CheckQ, sqlQ_normal::class.java)
                                nextIntent.putParcelableArrayListExtra("sqlID", sqlID)
                                startActivity(nextIntent)
                            }
                            "고급" -> {
                                val nextIntent = Intent(this@CheckQ, sqlQ_pro::class.java)
                                nextIntent.putParcelableArrayListExtra("sqlID", sqlID)
                                startActivity(nextIntent)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@CheckQ, "fail data set", Toast.LENGTH_SHORT).show()
                    }
                })

            }
        })

        binding.back.setOnClickListener {
            val intent = Intent(this, myPage::class.java)
            finish()
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

    private fun updateSQL(uid: String, step: String, sqlName: String){
        database = FirebaseDatabase.getInstance().getReference("Users")

        val user = mapOf<String,String>(
            "sqlStep" to step,
            "sqlName" to sqlName
        )

        database.child(uid).updateChildren(user)
    }

}