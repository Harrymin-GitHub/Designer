package com.minhr.design.module_core.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.minhr.design.common_base.dagger.mvp.BaseFragment
import com.minhr.design.common_base.dagger.mvp.BaseLazyFragment
import com.minhr.design.module_core.R.id.viewpager
import java.util.ArrayList

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class ADA_PagerDesignerType constructor(fm: FragmentManager, fragmentList: List<BaseLazyFragment>) : FragmentStatePagerAdapter(fm) {
    var mFragmentList = ArrayList<BaseLazyFragment>()

    init {
        mFragmentList = fragmentList as ArrayList<BaseLazyFragment>
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
}