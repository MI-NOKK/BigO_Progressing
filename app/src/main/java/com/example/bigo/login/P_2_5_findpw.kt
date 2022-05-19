package com.example.bigo.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.ActivityP25FindpwBinding

class P_2_5_findpw : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP25FindpwBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.back25.setOnClickListener {
            val intent = Intent(this, P_2_4findpw::class.java)
            startActivity(intent)
        }

        binding.next2.setOnClickListener {
            val intent = Intent(this, P_2_Login::class.java)
            startActivity(intent)
        }
    }
}