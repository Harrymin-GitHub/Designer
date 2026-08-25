package com.minhr.design.module_mall.db

import android.content.Context
import androidx.room.Room

/**
 * author : Harrymin
 * e-mail : minhongrui@126.com
 * date   : 2026/8/25 7:38 PM
 * desc   : Room 数据库单例入口
 */
class AppDatabaseHelper private constructor(context: Context) {
    private val dbName = "designer.db"

    val appDataBase: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        dbName
    )
        // version=1 时勿挂载 2→3 的 Migration；schema 变更时直接重建即可（演示 App）
        .fallbackToDestructiveMigration()
        .build()

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseHelper? = null

        fun getInstance(context: Context): AppDatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppDatabaseHelper(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
