package com.example.bigo.MainPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.bigo.CodingTest.P7_coTest
import com.example.bigo.R
import com.example.bigo.Sql.P8_sql
import com.example.bigo.databinding.FragmentP41homeBinding
import com.example.bigo.licence.licence
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class P4_1home : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. 뷰 바인딩 설정
        val binding = FragmentP41homeBinding.inflate(inflater, container, false)

        binding.lastView.adapter = lastViewAdapter(getAdList()) // 어댑터 생성
        binding.lastView.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로

        binding.toCote.setOnClickListener {
            val intent = Intent(activity, P7_coTest::class.java)
            startActivity(intent)
        }
        binding.toSql.setOnClickListener {
            val intent = Intent(activity, P8_sql::class.java)
            startActivity(intent)
        }
        binding.toGichu.setOnClickListener {
            val intent = Intent(activity, licence::class.java)
            startActivity(intent)
        }

        MobileAds.initialize(activity) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        // 3. 프래그먼트 레이아웃 뷰 반환
        return binding.root

    }
    private fun getAdList(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.lastbtn, R.drawable.lastbtn,
            R.drawable.lastbtn
        )
    }
}
