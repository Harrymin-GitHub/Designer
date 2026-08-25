package com.will.weiyuekotlin.component

import androidx.appcompat.app.AppCompatActivity
import dagger.Component


/**
 * desc: .
 * author: Will .
 * date: 2026/8/25 7:38 PM
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface HttpComponent {

    fun inject(activity: AppCompatActivity)

}
