package com.example.mytabstest

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.mytabstest.adapters.MyPagerAdapter
import com.example.mytabstest.fragments.ThirdFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(){

    private lateinit var tabs: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Tabs и Pager
        viewPager = findViewById(R.id.viewpager)
        tabs = findViewById(R.id.tabs)

        val myPagerAdapter = MyPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = myPagerAdapter

        tabs.setupWithViewPager(viewPager)

    }

}

