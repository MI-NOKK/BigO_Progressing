package com.example.bigo.MainPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.bigo.CodingTest.cotest
import com.example.bigo.CodingTest.cotestQ_new
import com.example.bigo.CodingTest.cotestQ_normal
import com.example.bigo.CodingTest.cotestQ_pro
import com.example.bigo.R
import com.example.bigo.Sql.*
import com.example.bigo.coParcel
import com.example.bigo.databinding.P4HomeBinding
import com.example.bigo.lcidParcel
import com.example.bigo.licence.licence
import com.example.bigo.licence.licenceQ
import com.example.bigo.sqlParcel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Home : Fragment() {

    val database = Firebase.database.getReference("Users")
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. 뷰 바인딩 설정
        val binding = P4HomeBinding.inflate(inflater, container, false)

        val adapter = lastViewAdapter(getLastList())
        binding.lastView.adapter = adapter // 어댑터 생성
        binding.lastView.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로

        binding.new2.setOnClickListener {
            binding.new1.visibility = View.GONE
            binding.new2.visibility = View.GONE
            binding.new3.visibility = View.GONE
            binding.nomal1.visibility = View.VISIBLE
            binding.nomal2.visibility = View.VISIBLE
            binding.nomal3.visibility = View.VISIBLE
        }
        binding.new3.setOnClickListener {
            binding.new1.visibility = View.GONE
            binding.new2.visibility = View.GONE
            binding.new3.visibility = View.GONE
            binding.pro1.visibility = View.VISIBLE
            binding.pro2.visibility = View.VISIBLE
            binding.pro3.visibility = View.VISIBLE
        }
        binding.nomal1.setOnClickListener {
            binding.nomal1.visibility = View.GONE
            binding.nomal2.visibility = View.GONE
            binding.nomal3.visibility = View.GONE
            binding.new1.visibility = View.VISIBLE
            binding.new2.visibility = View.VISIBLE
            binding.new3.visibility = View.VISIBLE
        }
        binding.nomal3.setOnClickListener {
            binding.nomal1.visibility = View.GONE
            binding.nomal2.visibility = View.GONE
            binding.nomal3.visibility = View.GONE
            binding.pro1.visibility = View.VISIBLE
            binding.pro2.visibility = View.VISIBLE
            binding.pro3.visibility = View.VISIBLE
        }
        binding.pro1.setOnClickListener {
            binding.pro1.visibility = View.GONE
            binding.pro2.visibility = View.GONE
            binding.pro3.visibility = View.GONE
            binding.new1.visibility = View.VISIBLE
            binding.new2.visibility = View.VISIBLE
            binding.new3.visibility = View.VISIBLE
        }
        binding.pro2.setOnClickListener {
            binding.pro1.visibility = View.GONE
            binding.pro2.visibility = View.GONE
            binding.pro3.visibility = View.GONE
            binding.nomal1.visibility = View.VISIBLE
            binding.nomal2.visibility = View.VISIBLE
            binding.nomal3.visibility = View.VISIBLE
        }

        var lcidList = arrayListOf<lcidParcel>()
        var coidList = arrayListOf<coParcel>()
        var sqlidList = arrayListOf<sqlParcel>()

        var intentLC: Intent? = null
        var intentCt: Intent? = null
        var intentSql: Intent? = null

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid.toString()
        adapter.setItemClickListener(object : lastViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                if (position == 0) {
                    intentLC = Intent(activity, licenceQ::class.java)
                    database.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val dateId = snapshot.child(uid).child("dateId").value
                            val lcId = snapshot.child(uid).child("lcId").value
                            lcidList.add(lcidParcel(dateId as String, lcId as String))
                            intentLC!!.putParcelableArrayListExtra("lcidList", lcidList)
                            startActivity(intentLC)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(activity, "fail data set", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else if (position == 1) {
                    database.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            when (snapshot.child(uid).child("level").value) {
                                "초급" -> {
                                    intentCt = Intent(activity, cotestQ_new::class.java)
                                }
                                "중급" -> {
                                    intentCt = Intent(activity, cotestQ_normal::class.java)
                                }
                                "고급" -> {
                                    intentCt = Intent(activity, cotestQ_pro::class.java)
                                }
                            }
                            val coId = snapshot.child(uid).child("coStep").value
                            val coName = snapshot.child(uid).child("coName").value
                            coidList.add(coParcel(coId as String, coName as String))
                            intentCt!!.putParcelableArrayListExtra("coID", coidList)
                            startActivity(intentCt)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(activity, "fail data set", Toast.LENGTH_SHORT).show()
                        }
                    })

                } else if (position == 2) {
                    database.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            when (snapshot.child(uid).child("level").value) {
                                "초급" -> {
                                    intentSql = Intent(activity, sqlQ_new::class.java)
                                }
                                "중급" -> {
                                    intentSql = Intent(activity, sqlQ_normal::class.java)
                                }
                                "고급" -> {
                                    intentSql = Intent(activity, sqlQ_pro::class.java)
                                }
                            }
                            val sqlId = snapshot.child(uid).child("sqlStep").value
                            val sqlName = snapshot.child(uid).child("sqlName").value
                            sqlidList.add(sqlParcel(sqlId as String, sqlName as String))
                            intentSql!!.putParcelableArrayListExtra("sqlID", sqlidList)
                            startActivity(intentSql)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(activity, "fail data set", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                }
            }
        })

        binding.toCote.setOnClickListener {
            val intent = Intent(activity, cotest::class.java)
            startActivity(intent)
        }
        binding.toSql.setOnClickListener {
            val intent = Intent(activity, sql::class.java)
            startActivity(intent)
        }
        binding.tolicense.setOnClickListener {
            val intent = Intent(activity, licence::class.java)
            startActivity(intent)
        }

        MobileAds.initialize(activity) {}

        val adRequest = AdRequest.Builder().build()
        binding.advertisingView.loadAd(adRequest)

        // 3. 프래그먼트 레이아웃 뷰 반환
        return binding.root

    }

    private fun getLastList(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.lastbtn,
            R.drawable.lastbtn,
            R.drawable.lastbtn
        )
    }
}


