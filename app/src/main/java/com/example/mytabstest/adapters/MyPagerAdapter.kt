package com.example.mytabstest.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mytabstest.fragments.FirstFragment
import com.example.mytabstest.fragments.SecondFragment
import com.example.mytabstest.fragments.ThirdFragment

class MyPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> {
                return ThirdFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Альбомы"
            1 -> "Избранное"
            else ->
                return "GPS"

        }
    }

}

