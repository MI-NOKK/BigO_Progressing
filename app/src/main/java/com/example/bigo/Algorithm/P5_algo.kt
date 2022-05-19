package com.example.bigo.Algorithm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bigo.MainPage.P4_Navi
import com.example.bigo.databinding.FragmentP5AlgoBinding

class P5_algo : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. 뷰 바인딩 설정
        val binding = FragmentP5AlgoBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            val intent = Intent(activity, P4_Navi::class.java)
            startActivity(intent)
        }
        binding.button1.setOnClickListener {
            val intent = Intent(activity, P5_1_algoEx::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(activity, P5_1_algoEx::class.java)
            startActivity(intent)
        }
        // 2. 프래그먼트 레이아웃 뷰 반환
        return binding.root
    }
}

