package com.example.bigo.Algorithm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bigo.MainPage.Navi
import com.example.bigo.databinding.P51AlgoBinding

class algo : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. 뷰 바인딩 설정
        val binding = P51AlgoBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            val intent = Intent(activity, Navi::class.java)
            startActivity(intent)
        }
        binding.bubble.setOnClickListener {
            val intent = Intent(activity, algoEx::class.java)
            intent.putExtra("bubble", String())
            startActivity(intent)
        }
        binding.queue.setOnClickListener {
            val intent = Intent(activity, algoEx::class.java)
            intent.putExtra("queue",String())
            startActivity(intent)
        }
        // 2. 프래그먼트 레이아웃 뷰 반환
        return binding.root
    }
}