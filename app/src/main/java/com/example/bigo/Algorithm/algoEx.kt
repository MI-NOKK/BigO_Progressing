package com.example.bigo.Algorithm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bigo.R
import com.example.bigo.databinding.P52AlgoExBinding

class algoEx : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P52AlgoExBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.back.setOnClickListener {
            val intent = Intent(this, algo::class.java)
            startActivity(intent)
        }

        if (intent.hasExtra("bubble")) {
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
            binding.videoView.start()

            var i = 1

            binding.rightBtn.setOnClickListener {
                i += 1
                if (i > 6) {
                    finish()
                }
                when (i) {
                    2 -> {
                        binding.videoView.setVideoURI(uri2)
                        binding.videoView.start()
                    }
                    3 -> {
                        binding.videoView.setVideoURI(uri3)
                        binding.videoView.start()
                    }
                    4 -> {
                        binding.videoView.setVideoURI(uri4)
                        binding.videoView.start()
                    }
                    5 -> {
                        binding.videoView.setVideoURI(uri5)
                        binding.videoView.start()
                    }
                    6 -> {
                        binding.videoView.setVideoURI(uri6)
                        binding.videoView.start()
                    }

                }
            }
            binding.leftBtn.setOnClickListener{
                i -= 1
                if (i < 1) {
                    binding.leftBtn.isEnabled = false
                    i = 1
                    binding.leftBtn.isEnabled = true
                }
                when(i) {
                    1 -> {
                        binding.videoView.setVideoURI(uri)
                        binding.videoView.start()
                    }
                    2 -> {
                        binding.videoView.setVideoURI(uri2)
                        binding.videoView.start()
                    }
                    3 -> {
                        binding.videoView.setVideoURI(uri3)
                        binding.videoView.start()
                    }
                    4 -> {
                        binding.videoView.setVideoURI(uri4)
                        binding.videoView.start()
                    }
                    5 -> {
                        binding.videoView.setVideoURI(uri5)
                        binding.videoView.start()
                    }
                    6 -> {
                        binding.videoView.setVideoURI(uri6)
                        binding.videoView.start()
                    }
                }
            }
        }
        if (intent.hasExtra("queue")) {
            val VIDEO_PATH_QUEUE = "android.resource://" + packageName + "/" + R.raw.bubble_v1
            val VIDEO_PATH_QUEUE2 = "android.resource://" + packageName + "/" + R.raw.bubble_v2
            val VIDEO_PATH_QUEUE3 = "android.resource://" + packageName + "/" + R.raw.bubble_v3
            val VIDEO_PATH_QUEUE4 = "android.resource://" + packageName + "/" + R.raw.bubble_v4
            val VIDEO_PATH_QUEUE5 = "android.resource://" + packageName + "/" + R.raw.bubble_v5

            val uri_queue: Uri = Uri.parse(VIDEO_PATH_QUEUE)
            val uri_queue2: Uri = Uri.parse(VIDEO_PATH_QUEUE2)
            val uri_queue3: Uri = Uri.parse(VIDEO_PATH_QUEUE3)
            val uri_queue4: Uri = Uri.parse(VIDEO_PATH_QUEUE4)
            val uri_queue5: Uri = Uri.parse(VIDEO_PATH_QUEUE5)

            binding.videoView.setVideoURI(uri_queue)
            binding.videoView.start()

            var i = 1

            binding.rightBtn.setOnClickListener {
                i += 1
                if (i > 5) {
                    finish()
                }
                when (i) {
                    2 -> {
                        binding.videoView.setVideoURI(uri_queue2)
                        binding.videoView.start()
                    }
                    3 -> {
                        binding.videoView.setVideoURI(uri_queue3)
                        binding.videoView.start()
                    }
                    4 -> {
                        binding.videoView.setVideoURI(uri_queue4)
                        binding.videoView.start()
                    }
                    5 -> {
                        binding.videoView.setVideoURI(uri_queue5)
                        binding.videoView.start()
                    }
                }
            }
            binding.leftBtn.setOnClickListener{
                i -= 1
                if (i < 1) {
                    binding.leftBtn.isEnabled = false
                    i = 1
                    binding.leftBtn.isEnabled = true

                }
                when(i) {
                    1 -> {
                        binding.videoView.setVideoURI(uri_queue)
                        binding.videoView.start()
                    }
                    2 -> {
                        binding.videoView.setVideoURI(uri_queue2)
                        binding.videoView.start()
                    }
                    3 -> {
                        binding.videoView.setVideoURI(uri_queue3)
                        binding.videoView.start()
                    }
                    4 -> {
                        binding.videoView.setVideoURI(uri_queue4)
                        binding.videoView.start()
                    }
                    5 -> {
                        binding.videoView.setVideoURI(uri_queue5)
                        binding.videoView.start()
                    }
                }
            }
        }
    }
}