package com.minhr.design.common_base.dagger.mvp.databinding

import androidx.databinding.ViewDataBinding
import androidx.annotation.Nullable
import com.minhr.design.common_base.BaseAppliction
import com.minhr.design.common_base.dagger.mvp.BaseContract
import com.minhr.design.common_base.dagger.mvp.BaseLazyFragment
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2-MVP-BaseFragment（支持DataBinding）
 */
abstract class BaseDBFragment<P : BaseContract.BasePresenter, M : BaseContract.BaseModel, DB : ViewDataBinding> : BaseLazyFragment() {
    @Nullable
    @Inject
    @JvmField
    var mPresenter: P? = null
    @Nullable
    @Inject
    @JvmField
    var mModel: M? = null
    protected var viewBinding: DB? = null


    override fun startEvents() {
        viewBinding = viewDataBinding as DB
        initDaggerInject(BaseAppliction.mApplicationComponent)
        val model = mModel
        if (mPresenter != null && model != null) {
            mPresenter?.attachViewModel(this, model)
        }
        startFragmentEvents()
    }

    abstract fun startFragmentEvents()
}