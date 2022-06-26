package com.example.bigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.bigo.MainPage.Navi
import com.example.bigo.login.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StartPage : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    val database = Firebase.database.getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p1_start)
        Handler().postDelayed({
            firebaseAuth = FirebaseAuth.getInstance()
            checkUser()
        }, DURATION)
    }

    companion object {
        private const val DURATION: Long = 1500
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun checkUser() {
        // if user is already logged in go to P3_surbay
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser?.uid.toString()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val surveyDo = snapshot.child(uid).child("survey").value.toString()
                if (firebaseUser != null) {
                    if (surveyDo == "On") {
                        startActivity(Intent(this@StartPage, Navi::class.java))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        finish()
                    } else {
                        startActivity(Intent(this@StartPage, survey::class.java))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        finish()
                    }
                } else {
                    startActivity(Intent(this@StartPage, Login::class.java))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@StartPage, "fail data set", Toast.LENGTH_SHORT).show()
            }
        })
    }
}