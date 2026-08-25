package com.minhr.design

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.minhr.design.common_base.bean.ErrorBean
import com.minhr.design.databinding.ActTestBinding

/**
 * author : Harrymin
 * e-mail : minhongrui@126.com
 * date   : 2026/8/25 7:38 PM
 * desc   : DataBinding 路由测试页
 */
@Route(path = "/appmodule/test")
class ACT_TestRouter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActTestBinding>(this, R.layout.act_test)
        binding.bean = ErrorBean().apply { msg = "DataBinding测试" }
    }
}
