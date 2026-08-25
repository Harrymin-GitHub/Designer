package com.will.weiyuekotlin.component

import com.will.weiyuekotlin.module.ApplicationModule
import com.will.weiyuekotlin.module.HttpModule

import dagger.Component

/**
 * desc: .
 * author: Will .
 * date: 2026/8/25 7:38 PM
 */
@Component(modules = [(ApplicationModule::class), (HttpModule::class)])
interface ApplicationComponent {
//
//    val application: BaseAppliction
//
//    val context: Context
//
//    fun getNetEaseApi(): NewsApi
//
//    fun getJanDanApi(): JanDanApi


}
