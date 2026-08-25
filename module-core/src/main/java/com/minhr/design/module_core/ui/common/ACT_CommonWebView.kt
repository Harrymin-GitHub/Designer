package com.minhr.design.module_core.ui.common

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.alibaba.android.arouter.facade.annotation.Route
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_base.dagger.mvp.BaseActivity
import com.minhr.design.common_base.dagger.mvp.BaseContract
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.module_core.R
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 公共的加载Url的WebView
 */
@Route(path = ARouterConfig.ACT_WEBVIEW)
class ACT_CommonWebView : BaseActivity<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    private val webview by bindView<WebView>(R.id.webview)
    private var webUrl: String? = null
    override fun getContentViewLayoutId(): Int = R.layout.act_common_webview

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
    }

    override fun startEvents() {
        intent.extras?.getString(ARouterConstants.WEB_TITLE)?.let { setHeaderTitle(it) }
        webUrl = intent.extras?.getString(ARouterConstants.WEB_URL)
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val url = webUrl?.trim().orEmpty()
        val settings = webview.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.loadsImagesAutomatically = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    dismissDialogLoading()
                } else {
                    showDialogLoading("")
                }
            }
        }
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                val next = request.url?.toString().orEmpty()
                if (next.isNotBlank()) {
                    view.loadUrl(next)
                }
                return true
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.isNotBlank()) {
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                showDialogLoading("")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                dismissDialogLoading()
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()
            }
        }

        if (url.isBlank()) {
            dismissDialogLoading()
            return
        }
        // 设置完成后再加载，避免部分站点因 JS/混合内容未就绪而白屏
        webview.loadUrl(url)
    }

    override fun onDestroy() {
        webview.stopLoading()
        webview.destroy()
        super.onDestroy()
    }
}
