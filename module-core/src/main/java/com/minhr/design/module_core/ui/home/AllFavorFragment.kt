package com.minhr.design.module_core.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.minhr.design.common_base.BaseAppliction
import com.minhr.design.common_base.dagger.mvp.BaseFragment
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.module_core.R
import com.minhr.design.module_core.adapter.ADA_PersonLike
import com.minhr.design.module_core.bean.*
import com.minhr.design.module_core.dagger2.DaggerCoreComponent
import com.minhr.design.module_core.mvp.contract.ShoppingContract
import com.minhr.design.module_core.mvp.model.ShoppingModel
import com.minhr.design.module_core.mvp.presenter.ShoppingPresenter
import androidx.recyclerview.widget.RecyclerView
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 * author : Harrymin
 * e-mail : minhongrui@126.com
 * date   : 2026/8/25 7:38 PM
 * desc   : 逛-底部-大家喜欢
 */
class AllFavorFragment : BaseFragment<ShoppingPresenter, ShoppingModel>(), ShoppingContract.View {
    private val recyclerview by bindView<RecyclerView>(R.id.recyclerview)
    private var mAdapter: ADA_PersonLike? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_all_favor
    override fun onFirstUserVisible() {
    }

    override fun onFirstUserInvisible() {
    }

    override fun onUserVisible() {
    }

    override fun onUserInvisible() {
    }

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerCoreComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startFragmentEvents() {
        mPresenter?.getPersonLike()

        mAdapter = ADA_PersonLike(mContext)
        recyclerview.layoutManager =
            LinearLayoutManager(mContext)
        recyclerview.adapter = mAdapter
    }

    override fun getCategoryList(dataList: List<CategoryEntity>) {
    }

    override fun getGoodsList(dataList: List<GoodsEntity>) {
    }

    /**
     * 精选
     */
    override fun getHandPickedGoods(bean: RecordsEntity) {

    }

    /**
     * 大家喜欢
     */
    override fun getPersonLike(dataList: List<AllfaverEntity>) {
        mAdapter?.update(dataList[0].list, true)
    }

}