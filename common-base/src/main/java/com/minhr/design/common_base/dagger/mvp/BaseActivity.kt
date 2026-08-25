package com.minhr.design.common_base.dagger.mvp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.minhr.design.common_base.BaseAppliction
import com.minhr.design.common_base.ext.registerAppReceiver
import com.minhr.design.common_base.R
import com.minhr.design.common_base.bean.ErrorBean
import com.minhr.design.common_base.config.constants.BroadCastConstant
import com.minhr.design.common_base.utils.StatusBarHelper
import com.minhr.design.common_ui.dialog.LoadingDialog
import com.minhr.design.common_ui.view.MultipleStatusView
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2_MVP-Activity的基类 (Activity动画、事件订阅EventBus/广播、状态栏，多状态View切换)
 */
abstract class BaseActivity<P : BaseContract.BasePresenter, M : BaseContract.BaseModel> : AppCompatActivity(), IBase, BaseContract.BaseView {
    @Nullable
    @Inject
    @JvmField
    var mPresenter: P? = null
    @Nullable
    @Inject
    @JvmField
    var mModel: M? = null
    @Nullable
    protected var mMultipleStatusView: MultipleStatusView? = null
    protected lateinit var mContext: Context
    protected var mIsBind: Boolean = false
    protected var mTransitionMode = BaseActivity.TransitionMode.RIGHT
    protected var mIsRegisterReceiver = false
    lateinit var mLoadingDialog: LoadingDialog

    /**
     * OverridePendingTransition
     */
    enum class TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE, ZOOM, NOON
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mLoadingDialog = LoadingDialog(this)

        setContentView(getContentViewLayoutId())
        //根据子类布局自定义的区域show多状态布局
        mMultipleStatusView = getLoadingMultipleStatusView()

        initDaggerInject(BaseAppliction.mApplicationComponent)
        // WebView 等无 MVP 注入的页面 mModel/mPresenter 为空，不可对 mModel 使用 !!
        val model = mModel
        if (mPresenter != null && model != null) {
            mPresenter?.attachViewModel(this, model)
        }

        //Activity默认动画为右进右出
        when (getOverridePendingTransitionMode(mTransitionMode)) {
            TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
            TransitionMode.RIGHT -> overridePendingTransition(R.anim.enter_trans, R.anim.exit_scale)
            TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
            TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, 0)
            TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
            TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            TransitionMode.ZOOM -> overridePendingTransition(R.anim.zoomin, R.anim.zoomout)
            TransitionMode.NOON -> Unit
        }

        //事件订阅
        if (isBindEventBus(mIsBind)) {
            EventBus.getDefault().register(this)
        }
        registerBroadCastReceiver()

        //设置沉浸式状态栏
        StatusBarHelper.setStatusBar(this, false, true)

        startEvents()
    }


    /**
     * 发送一个广播
     *
     * @param value
     */
    protected fun sendCommonBroadcast(value: Int) {
        sendBroadcast(this, value)
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
                var info = packageManager.getPackageInfo(packageName, 0)

                if (intent.action == info.packageName + BroadCastConstant.BROADCASE_ADDRESS) {
                    var bundle = intent.extras
                    var i = bundle!!.getInt(BroadCastConstant.BROADCASE_INTENT)
                    if (i != null) {
                        this@BaseActivity.onReceiveBroadcast(i, bundle)
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
            var info = packageManager.getPackageInfo(packageName, 0)
            registerAppReceiver(broadcastReceiver, IntentFilter(info.packageName + BroadCastConstant.BROADCASE_ADDRESS))
            mIsRegisterReceiver = true
        } catch (e: Exception) {

        }

    }

    protected open fun onReceiveBroadcast(intent: Int, bundle: Bundle) {
        if (intent == BroadCastConstant.LOGOUT) {
            finish()
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    fun setHeaderTitle(title: String) {

        if (TextUtils.isEmpty(title)) {
            return
        }
        val titleTV = findViewById<TextView>(R.id.tv_common_title) ?: return
        titleTV.text = title
    }

    fun onActionFinish(v: View) {
        finish()
    }

    override fun finish() {
        super.finish()
        when (getOverridePendingTransitionMode(mTransitionMode)) {
            TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
            TransitionMode.RIGHT -> overridePendingTransition(R.anim.enter_scale, R.anim.exit_trans)
            TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
            TransitionMode.BOTTOM -> overridePendingTransition(0, R.anim.bottom_out)
            TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
            TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            TransitionMode.ZOOM -> overridePendingTransition(R.anim.zoomin, R.anim.zoomout)
            TransitionMode.NOON -> Unit
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
                this.unregisterReceiver(broadcastReceiver)
            } catch (e: Exception) {
            } finally {
                broadcastReceiver = null
            }
        }
        mPresenter?.detachView()
        mPresenter?.onDestroy()
        mLoadingDialog.dismiss()
    }

    override fun showLoading() {
        mMultipleStatusView?.showLoading()
    }


    fun isBindEventBus(isBind: Boolean): Boolean {
        mIsBind = isBind
        return mIsBind
    }

    open fun getOverridePendingTransitionMode(transitionMode: BaseActivity.TransitionMode): BaseActivity.TransitionMode {
        mTransitionMode = transitionMode
        return mTransitionMode
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