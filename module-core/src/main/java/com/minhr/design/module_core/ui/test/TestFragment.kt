package com.minhr.design.module_core.ui.test

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import com.minhr.design.common_base.dagger.mvp.BaseFragment
import com.minhr.design.common_base.utils.ToastUtils
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.module_core.R
import com.minhr.design.module_core.adapter.ADA_TestFragment
import com.minhr.design.module_core.dagger2.DaggerCoreComponent
import com.minhr.design.module_core.mvp.contract.TestContract
import com.minhr.design.module_core.mvp.model.TestModel
import com.minhr.design.module_core.mvp.presenter.TestPresenter
import androidx.recyclerview.widget.RecyclerView
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class TestFragment : BaseFragment<TestPresenter, TestModel>(), TestContract.View {
    private val tvTxt by bindView<TextView>(R.id.tv_txt)
    private val multiplestatusview by bindView<MultipleStatusView>(R.id.multiplestatusview)
    private val recyclerview by bindView<RecyclerView>(R.id.recyclerview)
    override fun onFirstUserVisible() {
        Log.e("frag", " onFirstUserVisible()")
    }

    override fun onFirstUserInvisible() {
        Log.e("frag", "onFirstUserInvisible()")
    }

    override fun onUserVisible() {
        Log.e("frag", "onUserVisible()")
    }

    override fun onUserInvisible() {
        Log.e("frag", "onUserInvisible()")
    }

    override fun getContentViewLayoutId(): Int = R.layout.test_fragment

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = multiplestatusview

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerCoreComponent.builder().applicationComponent(mApplicationComponent).build().inject(this)
    }

    override fun startFragmentEvents() {
//        var bean = ErrorBean()
//        bean.msg = "Fragment中测试DataBinding"
//        viewBinding?.bean = bean
//        tv_txt.text = "Fragment-kotlin初始化View的值"
        tvTxt.text = "Fragment测试ButterKnife"
        Log.e("TAG", "TestFragment-Presenter=" + mPresenter)

        mMultipleStatusView?.showLoading()
        mPresenter?.getData("presenter init successful Fragment-MVP-测试")
        recyclerview.layoutManager =
            LinearLayoutManager(mContext)
        var mAdapter = ADA_TestFragment(mContext)
        recyclerview.adapter = mAdapter
        var data = ArrayList<String>()
        for (i in 0..50) {
            data.add("item=" + i)
        }
        mAdapter.update(data, true)
        Handler().postDelayed({
            mMultipleStatusView?.showContent()
        }, 2000)


    }

    override fun getData(result: String) {
        ToastUtils.makeShortToast(result)
    }
}