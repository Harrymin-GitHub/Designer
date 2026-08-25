package com.minhr.design.module_mall.db.bean

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * author : Harrymin
 * e-mail : minhongrui@126.com
 * date   : 2026/8/25 7:38 PM
 * desc   :
 */
@Entity(tableName = "history")
//indices = [(Index(value = *arrayOf("searchKeyWords"), unique = true))]保证searchKeyWords列的唯一性
//tableName = "history", primaryKeys = ["id", "searchKeyWords"]设置多个主键（编译出错：Error:Execution failed for task ':module-mall:kaptDebugKotlin'...）
class SearchHistoryBean {
    //设置主键，并且定义自增增
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var searchKeyWords: String? = null

    override fun toString(): String {
        return "SearchHistoryBean{" +
                "id=" + id +
                ", searchKeyWords='" + searchKeyWords + '\'' +
                '}'
    }
}
