package com.minhr.design.module_core.ui.common

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.text.TextUtils
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
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
    private var webTitle: String? = null
    private var webImage: String? = null
    private var webDesc: String? = null

    override fun getContentViewLayoutId(): Int = R.layout.act_common_webview

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
    }

    override fun startEvents() {
        webTitle = intent.extras?.getString(ARouterConstants.WEB_TITLE)
        webTitle?.let { setHeaderTitle(it) }
        webUrl = intent.extras?.getString(ARouterConstants.WEB_URL)
        webImage = intent.extras?.getString(ARouterConstants.WEB_IMAGE)
        webDesc = intent.extras?.getString(ARouterConstants.WEB_DESC)
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
        // 去掉 WebView 标记，降低被站点识别后强制跳 App 深链的概率
        val ua = settings.userAgentString.orEmpty()
        if (ua.contains("; wv")) {
            settings.userAgentString = ua.replace("; wv", "")
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
                return handleUrl(request.url?.toString().orEmpty())
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return handleUrl(url)
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

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && request.isForMainFrame) {
                    showLocalPreview("页面加载失败", webImage, "请检查网络后重试")
                }
            }

            @Deprecated("Deprecated in Java")
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    showLocalPreview("页面加载失败", webImage, description ?: "请检查网络后重试")
                }
            }
        }

        when {
            url.isBlank() || isLocalPreview(url) || isDeadH5Host(url) -> {
                showLocalPreview(
                    webTitle.orEmpty().ifBlank { "详情" },
                    webImage,
                    webDesc ?: "原 H5 页面已下线，当前为本地演示页"
                )
            }
            else -> webview.loadUrl(url)
        }
    }

    private fun isLocalPreview(url: String): Boolean {
        return url.startsWith(LOCAL_PREVIEW_SCHEME, ignoreCase = true)
    }

    private fun isDeadH5Host(url: String): Boolean {
        val host = Uri.parse(url).host?.lowercase().orEmpty()
        return host.contains("xiangqu.com") || host.contains("baidu.com")
    }

    private fun showLocalPreview(title: String, imageUrl: String?, tip: String) {
        dismissDialogLoading()
        val safeTitle = TextUtils.htmlEncode(title)
        val safeTip = TextUtils.htmlEncode(tip)
        val imageBlock = if (!imageUrl.isNullOrBlank()) {
            """<img src="${TextUtils.htmlEncode(imageUrl)}" alt=""/>"""
        } else {
            """<div class="placeholder">暂无封面</div>"""
        }
        val html = """
            <!DOCTYPE html>
            <html>
            <head>
              <meta charset="utf-8"/>
              <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
              <style>
                body{margin:0;background:#f5f5f5;font-family:sans-serif;color:#222}
                img{width:100%;display:block;background:#ddd}
                .placeholder{height:220px;display:flex;align-items:center;justify-content:center;background:#e8e8e8;color:#999}
                .title{padding:16px 16px 8px;font-size:20px;font-weight:600;line-height:1.4}
                .tip{padding:0 16px 24px;font-size:14px;color:#888;line-height:1.6}
              </style>
            </head>
            <body>
              $imageBlock
              <div class="title">$safeTitle</div>
              <div class="tip">$safeTip</div>
            </body>
            </html>
        """.trimIndent()
        webview.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
    }

    /**
     * http/https 交给 WebView；自定义协议不可 loadUrl，否则会落到「网页无法打开」。
     */
    private fun handleUrl(rawUrl: String): Boolean {
        val next = rawUrl.trim()
        if (next.isBlank()) return true
        if (isLocalPreview(next)) {
            showLocalPreview(
                webTitle.orEmpty().ifBlank { "详情" },
                webImage,
                webDesc ?: "原 H5 页面已下线，当前为本地演示页"
            )
            return true
        }
        if (isWebUrl(next) && !isDeadH5Host(next)) {
            return false
        }
        if (isWebUrl(next) && isDeadH5Host(next)) {
            showLocalPreview(
                webTitle.orEmpty().ifBlank { "详情" },
                webImage,
                webDesc ?: "原 H5 页面已下线，当前为本地演示页"
            )
            return true
        }
        return interceptAppScheme(next)
    }

    private fun isWebUrl(url: String): Boolean {
        return url.startsWith("http://", ignoreCase = true)
            || url.startsWith("https://", ignoreCase = true)
            || url.startsWith("about:", ignoreCase = true)
            || url.startsWith("javascript:", ignoreCase = true)
    }

    /**
     * 百度等站点会跳 baiduboxapp:// / intent://。交给系统常仍提示「网页无法打开」，直接吞掉。
     */
    private fun interceptAppScheme(url: String): Boolean {
        val lower = url.lowercase()
        val blockedPrefixes = listOf(
            "baiduboxapp://",
            "baidubceapp://",
            "intent://",
            "tbopen://",
            "tmall://",
            "openapp.jdmobile://",
            "weixin://",
            "alipays://",
            "vipshop://"
        )
        if (blockedPrefixes.any { lower.startsWith(it) }) {
            return true
        }
        return try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addCategory(Intent.CATEGORY_BROWSABLE)
            })
            true
        } catch (_: ActivityNotFoundException) {
            true
        } catch (_: Exception) {
            true
        }
    }

    override fun onDestroy() {
        webview.stopLoading()
        webview.destroy()
        super.onDestroy()
    }

    companion object {
        const val LOCAL_PREVIEW_SCHEME = "app://local/preview"
    }
}
