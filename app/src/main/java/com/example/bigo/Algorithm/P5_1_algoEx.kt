package com.example.bigo.Algorithm

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bigo.R
import com.example.bigo.databinding.ActivityP51AlgoExBinding

class P5_1_algoEx : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP51AlgoExBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val VIDEO_PATH = "android.resource://" + packageName + "/" + R.raw.bubble_v1
        val VIDEO_PATH2 = "android.resource://" + packageName + "/" + R.raw.bubble_v2
        val VIDEO_PATH3 = "android.resource://" + packageName + "/" + R.raw.bubble_v3
        val VIDEO_PATH4 = "android.resource://" + packageName + "/" + R.raw.bubble_v4
        val VIDEO_PATH5 = "android.resource://" + packageName + "/" + R.raw.bubble_v5
        val VIDEO_PATH6 = "android.resource://" + packageName + "/" + R.raw.bubble_v6

        val uri: Uri = Uri.parse(VIDEO_PATH)
        val uri2: Uri = Uri.parse(VIDEO_PATH2)
        val uri3: Uri = Uri.parse(VIDEO_PATH3)
        val uri4: Uri = Uri.parse(VIDEO_PATH4)
        val uri5: Uri = Uri.parse(VIDEO_PATH5)
        val uri6: Uri = Uri.parse(VIDEO_PATH6)
        binding.videoView.setVideoURI(uri)
        binding.videoView2.setVideoURI(uri2)
        binding.videoView3.setVideoURI(uri3)
        binding.videoView4.setVideoURI(uri4)
        binding.videoView5.setVideoURI(uri5)
        binding.videoView6.setVideoURI(uri6)

        binding.videoView.start()

        var i = 1

        binding.button2.setOnClickListener {
            i += 1
            if (i > 6) {
                binding.button2.isEnabled = false
                i = 6
                binding.button2.isEnabled = true
            }
            binding.videoView.visibility = View.INVISIBLE
            binding.videoView2.visibility = View.INVISIBLE
            binding.videoView3.visibility = View.INVISIBLE
            binding.videoView4.visibility = View.INVISIBLE
            binding.videoView5.visibility = View.INVISIBLE
            binding.videoView6.visibility = View.INVISIBLE
            binding.textView.visibility = View.INVISIBLE
            binding.textView2.visibility = View.INVISIBLE
            when (i) {
                2 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView2.visibility = View.VISIBLE
                    binding.videoView2.start()
                }
                3 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView3.visibility = View.VISIBLE
                    binding.videoView3.start()
                }
                4 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView4.visibility = View.VISIBLE
                    binding.videoView4.start()
                }
                5 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView5.visibility = View.VISIBLE
                    binding.videoView5.start()
                }
                6 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView6.visibility = View.VISIBLE
                    binding.videoView6.start()
                }

            }
        }
        binding.button.setOnClickListener {
            i -= 1
            if (i < 1) {
                binding.button.isEnabled = false
                i = 1
                binding.button.isEnabled = true
            }
            binding.videoView.visibility = View.INVISIBLE
            binding.videoView2.visibility = View.INVISIBLE
            binding.videoView3.visibility = View.INVISIBLE
            binding.videoView4.visibility = View.INVISIBLE
            binding.videoView5.visibility = View.INVISIBLE
            binding.videoView6.visibility = View.INVISIBLE
            binding.textView.visibility = View.INVISIBLE
            binding.textView2.visibility = View.INVISIBLE
            when (i) {
                1 -> {
                    binding.textView.visibility = View.VISIBLE
                    binding.videoView.visibility = View.VISIBLE
                    binding.videoView.start()
                }
                2 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView2.visibility = View.VISIBLE
                    binding.videoView2.start()
                }
                3 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView3.visibility = View.VISIBLE
                    binding.videoView3.start()
                }
                4 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView4.visibility = View.VISIBLE
                    binding.videoView4.start()
                }
                5 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView5.visibility = View.VISIBLE
                    binding.videoView5.start()
                }
                6 -> {
                    binding.textView2.visibility = View.VISIBLE
                    binding.videoView6.visibility = View.VISIBLE
                    binding.videoView6.start()
                }
            }
        }
    }
}
