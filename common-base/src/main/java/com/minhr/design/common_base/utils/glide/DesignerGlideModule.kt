package com.minhr.design.common_base.utils.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.io.InputStream
import java.util.concurrent.TimeUnit

/**
 * Glide 全局配置：OkHttp 下载 + HTTP/磁盘缓存 + 默认解码策略，加速首屏与列表图片加载。
 */
@GlideModule
class DesignerGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(context).build()
        builder.setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))
        // 约 250MB 磁盘缓存，减少二次进入时的网络请求
        builder.setDiskCache(
            InternalCacheDiskCacheFactory(context, "glide_img_cache", 250L * 1024 * 1024)
        )
        builder.setDefaultRequestOptions(
            RequestOptions()
                .format(DecodeFormat.PREFER_RGB_565)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        )
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val httpCache = Cache(File(context.cacheDir, "glide_http_cache"), 100L * 1024 * 1024)
        val client = OkHttpClient.Builder()
            .cache(httpCache)
            .connectTimeout(12, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(client)
        )
    }

    override fun isManifestParsingEnabled(): Boolean = false
}
