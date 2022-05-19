package com.example.bigo.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.ActivityP24findpwBinding

class P_2_4findpw : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP24findpwBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.back24.setOnClickListener {
            val intent = Intent(this, P_2_Login::class.java)
            startActivity(intent)
        }

        binding.next25.setOnClickListener {
            val intent = Intent(this, P_2_5_findpw::class.java)
            startActivity(intent)
        }
    }
}