package com.example.bigo.licence

import android.R
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
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


class licenceQ1 : AppCompatActivity() {

    val lcQuestion = FirebaseFirestore.getInstance()
    private var packageRecyclerView: RecyclerView? = null
    lateinit var dateId : String
    lateinit var lcId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP92GuchulQBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*val itemList = arrayListOf<licenceQData1>()
        val adapter = licenceQAdapter1(itemList, this)*/

        var lcidList = intent.getParcelableArrayListExtra<lcidParcel>("lcidList")
        dateId = lcidList?.get(0)?.dateId.toString()
        lcId = lcidList?.get(0)?.lcId.toString()

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
        lcQuestion.collection("licence").document(lcId)
            .collection("Date").document(dateId)
            .collection("question")
            .get().addOnSuccessListener { document ->
                modelList.clear()
                for (dc in document) {
                    priceList = ArrayList()
                    priceList.add(dc["example01"] as? String?)
                    priceList.add(dc["example02"] as? String?)
                    priceList.add(dc["example03"] as? String?)
                    priceList.add(dc["example04"] as? String?)
                    val item = licenceQData1(dc["qusetion"] as? String?, priceList)
                    modelList.add(item)
                }
                packageRecyclerView?.adapter?.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
        return modelList
    }
}






