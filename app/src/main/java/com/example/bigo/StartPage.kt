package com.example.bigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.bigo.login.P_2_Login
import com.google.firebase.auth.FirebaseAuth

class StartPage : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_p1)
        Handler().postDelayed({
            firebaseAuth = FirebaseAuth.getInstance()
            checkUser()
        },DURATION)

    }

    companion object {
        private const val DURATION : Long = 1500
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun checkUser() {
        // if user is already logged in go to P3_surbay
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser !=null){
            startActivity(Intent(this, P3_surbay::class.java))
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            finish()
        }
        else{
            startActivity(Intent(this, P_2_Login::class.java))
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            finish()
        }
    }
}