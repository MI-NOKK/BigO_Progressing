package com.example.bigo.myPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.P64ChangePwBinding

class chPW : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P64ChangePwBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.back.setOnClickListener {
            val intent = Intent(this, myPage::class.java)
            finish()
        }
    }
}