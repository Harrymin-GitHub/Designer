package com.minhr.design.module_mall.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.minhr.design.module_mall.db.bean.SearchHistoryBean
import com.minhr.design.module_mall.db.dao.HistoryDao

/**
 * author : Harrymin
 * e-mail : minhongrui@126.com
 * date   : 2026/8/25 7:38 PM
 * desc   : 商城模块 Room 数据库（搜索历史等）
 */
@Database(entities = [SearchHistoryBean::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /**
     * RoomDatabase 提供直接访问底层数据库实现，通过抽象方法返回具体 Dao。
     */
    abstract fun historyDao(): HistoryDao

    companion object {
        // 数据库变动添加 Migration（当前 version=1，未启用）
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE IF EXISTS user")
            }
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 预留：后续升版本时在此写 ALTER TABLE 等语句
            }
        }
    }
}
