package com.minhr.design.common_base.dagger.mvp

import androidx.databinding.ViewDataBinding
import androidx.annotation.Nullable
import com.minhr.design.common_base.BaseAppliction
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2-MVP-BaseFragment
 */
abstract class BaseFragment<P : BaseContract.BasePresenter, M : BaseContract.BaseModel> : BaseLazyFragment() {
    @Nullable
    @Inject
    @JvmField
    var mPresenter: P? = null
    @Nullable
    @Inject
    @JvmField
    var mModel: M? = null


    override fun startEvents() {
        initDaggerInject(BaseAppliction.mApplicationComponent)
        val model = mModel
        if (mPresenter != null && model != null) {
            mPresenter?.attachViewModel(this, model)
        }
        startFragmentEvents()
    }

    abstract fun startFragmentEvents()
}