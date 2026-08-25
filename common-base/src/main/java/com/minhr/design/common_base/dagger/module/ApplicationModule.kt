package com.will.weiyuekotlin.module

import android.content.Context
import com.minhr.design.common_base.BaseAppliction

import dagger.Module
import dagger.Provides

/**
 * desc:
 * author: Will .
 * date: 2026/8/25 7:38 PM
 */
@Module
class ApplicationModule(private val mContext: Context) {

    @Provides
    internal fun provideApplication(): BaseAppliction {
        return mContext.applicationContext as BaseAppliction
    }

    @Provides
    internal fun provideContext(): Context {
        return mContext
    }
}
