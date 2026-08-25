package com.minhr.design.module_core.ui.test

import android.os.Handler
import android.util.Log
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.minhr.design.common_base.dagger.mvp.BaseActivity
import com.minhr.design.common_base.utils.ToastUtils
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.module_core.R
import com.minhr.design.module_core.dagger2.DaggerCoreComponent
import com.minhr.design.module_core.mvp.contract.TestContract
import com.minhr.design.module_core.mvp.model.TestModel
import com.minhr.design.module_core.mvp.presenter.TestPresenter
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 测试Dagger2+MVP 架构
 */
@Route(path = "/base/act_testdagger")
class TestDaggerActivity : BaseActivity<TestPresenter, TestModel>(), TestContract.View {
    private val tv_head by bindView<TextView>(R.id.tv_head)
    private val multiplestatusview by bindView<MultipleStatusView>(R.id.multiplestatusview)
    override fun getData(result: String) {
        ToastUtils.makeShortToast(result)
        Log.e("TAG", "result=" + result)
//        var bean = ErrorBean()
//        bean.msg = result
//        viewDataBinding?.bean = bean
    }

    override fun getContentViewLayoutId(): Int = R.layout.test

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = multiplestatusview

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerCoreComponent.builder().applicationComponent(mApplicationComponent).build().inject(this)
    }

    override fun startEvents() {
        tv_head.setText("Dagger+MVP-Activity中测试ButterKnife")
        mMultipleStatusView?.showLoading()
        mPresenter?.getData("presenter init successful Activity-MVP-测试")
        Handler().postDelayed({
            mMultipleStatusView?.showContent()
        }, 2000)

    }

    override fun getOverridePendingTransitionMode(transitionMode: TransitionMode): TransitionMode {
        return super.getOverridePendingTransitionMode(TransitionMode.BOTTOM)
    }
}