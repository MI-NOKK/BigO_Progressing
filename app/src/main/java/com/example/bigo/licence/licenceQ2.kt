package com.example.bigo.licence

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.databinding.ActivityP92GuchulQBinding
import com.example.bigo.lcidParcel
import com.example.bigo.testParcel
import com.google.firebase.firestore.FirebaseFirestore
import licenceQAdapter1


class licenceQ2 : AppCompatActivity() {

    val lcQuestion = FirebaseFirestore.getInstance()
    private var packageRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP92GuchulQBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*val itemList = arrayListOf<licenceQData1>()
        val adapter = licenceQAdapter1(itemList, this)*/

        var lcidList = intent.getParcelableArrayListExtra<lcidParcel>("lcidList")
        val dateId = lcidList?.get(0)?.dateId.toString()
        val lcId = lcidList?.get(0)?.lcId.toString()

        /*binding.recycleGichul.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleGichul.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.recycleGichul!!.context,
            LinearLayoutManager(this).orientation
        )
        var priceList: MutableList<String?> = ArrayList()
        lcQuestion.collection("licence").document(lcId)
            .collection("Date").document(dateId)
            .collection("question")
            .get().addOnSuccessListener { document ->
                itemList.clear()
                for (dc in document) {
                    priceList.add(dc["example01"] as? String?)
                    priceList.add(dc["example02"] as? String?)
                    priceList.add(dc["example03"] as? String?)
                    priceList.add(dc["example04"] as? String?)
                    val item = licenceQData1(dc["qusetion"] as? String?, priceList)
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }*/
        packageRecyclerView = findViewById<View>(com.example.bigo.R.id.recycleGichul) as RecyclerView
        val recyclerLayoutManager = LinearLayoutManager(this)
        packageRecyclerView!!.layoutManager = recyclerLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            packageRecyclerView!!.context,
            recyclerLayoutManager.orientation
        )
        packageRecyclerView!!.addItemDecoration(dividerItemDecoration)
        val recyclerViewAdapter = licenceQAdapter1(getPackages() as ArrayList<licenceQData1>, this)
        packageRecyclerView!!.adapter = recyclerViewAdapter

        var answer = Array<String>(20, { "" })
        answer = recyclerViewAdapter.getAnswer()
        var lcAnswer = arrayListOf<testParcel>()
        var nextIntent = Intent(this, licenceSol::class.java)
        binding.result.setOnClickListener {
            lcAnswer.add(testParcel(answer))
            nextIntent.putParcelableArrayListExtra("lcAnswer", lcAnswer)
            startActivity(nextIntent)
        }
    }
    private fun getPackages(): List<licenceQData1> {
        val modelList: MutableList<licenceQData1> = ArrayList<licenceQData1>()
        var priceList: MutableList<String?> = ArrayList()
        priceList.add("$33 Monthly")
        priceList.add("$155 Half Yearly")
        priceList.add("$277 Yearly")
        modelList.add(licenceQData1("All English TV Channels", priceList))
        priceList = ArrayList()
        priceList.add("$45 Monthly")
        priceList.add("$225 Half Yearly")
        priceList.add("$410 Yearly")
        modelList.add(licenceQData1("HD Movie TV Channels", priceList))
        priceList = ArrayList()
        priceList.add("$50 Monthly")
        priceList.add("$288 Half Yearly")
        priceList.add("$545 Yearly")
        modelList.add(licenceQData1("All Sports Channels", priceList))
        priceList = ArrayList()
        priceList.add("$25 Monthly")
        priceList.add("$115 Half Yearly")
        priceList.add("$200 Yearly")
        modelList.add(licenceQData1("All News Channels", priceList))
        priceList = ArrayList()
        priceList.add("$65 Monthly")
        priceList.add("$690 Yearly")
        modelList.add(licenceQData1("Regional TV Channels", priceList))
        return modelList
    }
}






