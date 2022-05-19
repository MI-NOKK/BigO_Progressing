package com.example.bigo.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.R
import com.example.bigo.databinding.ActivityP21newaccountBinding
import com.example.bigo.databinding.ActivityP22newacBinding

class P_2_2newac : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP22newacBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.back22.setOnClickListener {
            val intent = Intent(this, P_2_1newaccount::class.java)
            startActivity(intent)
        }

        binding.next23.setOnClickListener {
            val intent = Intent(this, P_2_3newac::class.java)
            startActivity(intent)
        }

    }
}