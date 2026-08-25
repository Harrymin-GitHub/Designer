package com.minhr.design.module_mall.mvp.presenter

import com.minhr.design.common_base.dagger.mvp.BasePresenter
import com.minhr.design.common_base.net.RetrofitManager
import com.minhr.design.common_base.net.RxObserverListener
import com.minhr.design.module_mall.bean.CommentBean
import com.minhr.design.module_mall.bean.GoodsContentBean
import com.minhr.design.module_mall.bean.GoodsDesBean
import com.minhr.design.module_mall.bean.GoodsMockData
import com.minhr.design.module_mall.bean.RevelentBean
import com.minhr.design.module_mall.mvp.contract.GoodsContract
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品详情
 */
class GoodsPresenter @Inject constructor() : BasePresenter<GoodsContract.View, GoodsContract.Model>(), GoodsContract.Presenter {
    override fun getGoodsContent(productId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getGoodsContent(productId), object : RxObserverListener<GoodsContentBean>(mView) {
            override fun onSuccess(result: GoodsContentBean?) {
                mView?.getGoodsContent(result ?: GoodsMockData.goodsContent(productId))
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                // api.xiangqu.com 已下线，用本地数据保证详情页可演示（不 Toast）
                mView?.dismissDialogLoading()
                mView?.getGoodsContent(GoodsMockData.goodsContent(productId))
            }
        }))
    }

    override fun getGoodsDescription(productId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getGoodsDescription(productId), object : RxObserverListener<List<GoodsDesBean>>(mView) {
            override fun onSuccess(result: List<GoodsDesBean>?) {
                val list = if (result.isNullOrEmpty()) GoodsMockData.goodsDescription() else result
                mView?.getGoodsDescription(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getGoodsDescription(GoodsMockData.goodsDescription())
            }
        }))
    }

    override fun getGoodsCommentList(productId: String, type: Int) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getGoodsCommentList(productId, type), object : RxObserverListener<List<CommentBean>>(mView) {
            override fun onSuccess(result: List<CommentBean>?) {
                val list = if (result.isNullOrEmpty()) GoodsMockData.commentList() else result
                mView?.getGoodsCommentList(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getGoodsCommentList(GoodsMockData.commentList())
            }
        }))
    }

    override fun getRevelentGoodsList(productId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getRevelentGoodsList(productId), object : RxObserverListener<RevelentBean>(mView) {
            override fun onSuccess(result: RevelentBean?) {
                mView?.getRevelentGoodsList(result ?: GoodsMockData.revelentGoods())
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getRevelentGoodsList(GoodsMockData.revelentGoods())
            }
        }))
    }
}
