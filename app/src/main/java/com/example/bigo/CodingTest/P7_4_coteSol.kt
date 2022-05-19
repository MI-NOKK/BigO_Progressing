package com.example.bigo.CodingTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.ActivityP74CoteSolBinding

class P7_4_coteSol : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP74CoteSolBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button3.setOnClickListener {
            val intent = Intent(this, P7_coTest::class.java)
            startActivity(intent)
        }
    }
}