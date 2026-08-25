package com.minhr.design.module_core.ui.test

import android.os.Handler
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.BaseAppliction
import com.minhr.design.common_base.bean.ErrorBean
import com.minhr.design.common_base.dagger.mvp.databinding.BaseDBActivity
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.module_core.R
import com.minhr.design.module_core.dagger2.DaggerCoreComponent
import com.minhr.design.module_core.databinding.ActTestMvpBinding
import com.minhr.design.module_core.mvp.model.TestModel
import com.minhr.design.module_core.mvp.presenter.TestPresenter
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Activity测试
 */
@Route(path = "/core/search")
class TestMVPActivity : BaseDBActivity<TestPresenter, TestModel, ActTestMvpBinding>() {
    private val tv_txt by bindView<TextView>(R.id.tv_txt)
    override fun getContentViewLayoutId(): Int {
        return R.layout.act_test_mvp
    }

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerCoreComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startEvents() {
        mMultipleStatusView?.showLoading()
        Handler().postDelayed({
            mMultipleStatusView?.showContent()
            var fragment = TestFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fl_container, fragment)
            transaction.commitNowAllowingStateLoss()
        }, 2000)

        var bean = ErrorBean()
        bean.msg = "Activity中的DataBinding测试"
        viewDataBinding?.bean = bean
//
        tv_txt.text = "测试ButterKnife成功,点我跳转"
//
        tv_txt.setOnClickListener({
                        ARouter.getInstance().build("/base/act_testdagger").navigation()
//            ARouter.getInstance().build("/appmodule/test").navigation()
        })
    }
}