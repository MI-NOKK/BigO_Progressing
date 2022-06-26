package com.example.bigo.MainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bigo.Algorithm.algo
import com.example.bigo.myPage.myPage
import com.example.bigo.R
import com.example.bigo.databinding.P4NavigationBinding

private const val TAG_HOME = "navigation_home"
private const val TAG_ALGO = "naviagation_algo"
private const val TAG_MY_PAGE = "navigation_mypg"

class Navi : AppCompatActivity() {

    private lateinit var binding: P4NavigationBinding

    private var doubleBackToExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P4NavigationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setFragment(TAG_HOME, Home())

        binding.bottomNavi.setOnNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.navigation_home -> setFragment(TAG_HOME, Home())
                R.id.navigation_algo -> setFragment(TAG_ALGO, algo())
                R.id.navigation_mypg -> setFragment(TAG_MY_PAGE, myPage())
            }
            true
        }

    }
    override fun onBackPressed() {
        if (doubleBackToExit) {
            finishAffinity()
        } else {
            Toast.makeText(this, "종료하시려면 뒤로가기를 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
            doubleBackToExit = true
            runDelayed(1500L) {
                doubleBackToExit = false
            }
        }
    }

    fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }


    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        //트랜잭션에 tag로 전달된 fragment가 없을 경우 add
        if(manager.findFragmentByTag(tag) == null){
            ft.add(R.id.mainframe, fragment, tag)
        }

        //작업이 수월하도록 manager에 add되어있는 fragment들을 변수로 할당해둠
        val home = manager.findFragmentByTag(TAG_HOME)
        val category = manager.findFragmentByTag(TAG_ALGO)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        //모든 프래그먼트 hide
        if(category!=null){
            ft.hide(category)
        }
        if(home!=null){
            ft.hide(home)
        }
        if(myPage!=null){
            ft.hide(myPage)
        }

        //선택한 항목에 따라 그에 맞는 프래그먼트만 show
        if(tag == TAG_ALGO){
            if(category!=null){
                ft.show(category)
            }
        }
        else if(tag == TAG_HOME){
            if(home!=null){
                ft.show(home)
            }
        }
        else if(tag == TAG_MY_PAGE){
            if(myPage!=null){
                ft.show(myPage)
            }
        }

        //마무리
        ft.commitAllowingStateLoss()
        //ft.commit()

    }

}
