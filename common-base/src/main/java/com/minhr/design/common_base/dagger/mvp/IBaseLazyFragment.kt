package com.minhr.design.common_base.dagger.mvp

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Fragment懒加载抽离的接口
 */
interface IBaseLazyFragment {
    fun onFirstUserVisible()
    fun onFirstUserInvisible()
    fun onUserVisible()
    fun onUserInvisible()
}