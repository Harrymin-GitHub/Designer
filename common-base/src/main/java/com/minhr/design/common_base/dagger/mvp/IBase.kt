package com.minhr.design.common_base.dagger.mvp

import com.minhr.design.common_ui.view.MultipleStatusView
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2_MVP - Activity和Fragment抽离出的公共的接口
 */
interface IBase {
    fun getContentViewLayoutId(): Int
    fun getLoadingMultipleStatusView(): MultipleStatusView?
    fun initDaggerInject(mApplicationComponent: ApplicationComponent)
    fun startEvents()
}