package com.minhr.design.common_base.dagger.mvp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minhr.design.common_base.bean.ErrorBean
import com.minhr.design.common_base.config.constants.BroadCastConstant
import com.minhr.design.common_base.ext.registerAppReceiver
import com.minhr.design.common_ui.dialog.LoadingDialog
import com.minhr.design.common_ui.view.MultipleStatusView
import org.greenrobot.eventbus.EventBus

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger-MVP-Fragment懒加载
 */
abstract class BaseLazyFragment : Fragment(), IBase, IBaseLazyFragment, BaseContract.BaseView {
    lateinit var mLoadingDialog: LoadingDialog
    protected var mIsBind: Boolean = false
    protected var mIsRegisterReceiver = false
    protected var viewDataBinding: ViewDataBinding? = null
    protected lateinit var mContext: Context
    @Nullable
    protected var mMultipleStatusView: MultipleStatusView? = null

    private var isFirstResume = true
    private var isFirstVisible = true
    private var isFirstInvisible = true
    private var isPrepared: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoadingDialog = LoadingDialog(mContext)
        //事件订阅
        if (isBindEventBus(mIsBind)) {
            EventBus.getDefault().register(this)
        }
        registerBroadCastReceiver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getContentViewLayoutId() != 0) {
            return inflater.inflate(getContentViewLayoutId(), null)
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //根据子类布局自定义的区域show多状态布局
        mMultipleStatusView = getLoadingMultipleStatusView()

        startEvents()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPrepare()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false
            return
        }
        if (userVisibleHint) {
            onUserVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint) {
            onUserInvisible()
        }
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false
                initPrepare()
            } else {
                onUserVisible()
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false
                onFirstUserInvisible()
            } else {
                onUserInvisible()
            }
        }
    }


    @Synchronized private fun initPrepare() {
        if (isPrepared) {
            onFirstUserVisible()
        } else {
            isPrepared = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mIsBind) {
            EventBus.getDefault().unregister(this)
        }
        if (mIsRegisterReceiver && broadcastReceiver != null) {
            try {
                mIsRegisterReceiver = false
                activity?.unregisterReceiver(broadcastReceiver)
            } catch (e: Exception) {
            } finally {
                broadcastReceiver = null
            }
        }
    }

    /**
     * 发送一个广播
     *
     * @param value
     */
    protected fun sendCommonBroadcast(value: Int) {
        sendBroadcast(activity!!, value)
    }

    /**
     * 发送一个广播
     *
     * @param value
     */
    open fun sendBroadcast(context: Context, value: Int) {
        try {
            val info = context.packageManager.getPackageInfo(context.packageName, 0)
            val intent = Intent()
            intent.action = info.packageName + BroadCastConstant.BROADCASE_ADDRESS
            intent.putExtra(BroadCastConstant.BROADCASE_INTENT, value)
            context.sendBroadcast(intent)
        } catch (e: PackageManager.NameNotFoundException) {
        }

    }

    //广播接收器
    private var broadcastReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                val packageName = activity?.packageName ?: return
                val info = activity?.packageManager?.getPackageInfo(packageName, 0)

                if (intent.action == info?.packageName + BroadCastConstant.BROADCASE_ADDRESS) {
                    var bundle = intent.extras
                    var i = bundle!!.getInt(BroadCastConstant.BROADCASE_INTENT)
                    if (i != null) {
                        onReceiveBroadcast(i, bundle)
                    }
                }
            } catch (e: PackageManager.NameNotFoundException) {
            }

        }
    }

    /**
     * 注册广播
     */
    private fun registerBroadCastReceiver() {
        try {
            val packageName = activity?.packageName ?: return
            val info = activity?.packageManager?.getPackageInfo(packageName, 0)
            activity?.registerAppReceiver(
                broadcastReceiver,
                IntentFilter((info?.packageName.orEmpty()) + BroadCastConstant.BROADCASE_ADDRESS)
            )
            mIsRegisterReceiver = true
        } catch (e: Exception) {

        }

    }

    protected open fun onReceiveBroadcast(intent: Int, bundle: Bundle) {
    }

    open fun isBindEventBus(isBind: Boolean): Boolean {
        mIsBind = isBind
        return mIsBind
    }

    override fun showLoading() {
        mMultipleStatusView?.showLoading()
    }

    override fun showDialogLoading(msg: String) {
        if (!TextUtils.isEmpty(msg)) mLoadingDialog.setTitleText(msg).show() else mLoadingDialog.show()
    }

    override fun dismissDialogLoading() {
        mLoadingDialog.dismiss()
    }

    override fun showBusinessError(error: ErrorBean) {
        mMultipleStatusView?.showError()
    }

    override fun showException(error: ErrorBean) {
        if (com.minhr.design.common_base.utils.NetUtils.isNetworkConnected) {
            mMultipleStatusView?.showError()
        } else {
            mMultipleStatusView?.showNoNetwork()
        }
    }
}