package com.minhr.design.common_base.utils.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.minhr.design.common_base.utils.glide.transform.CornerOriginSizeTransform

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 统一图片加载（按 View 生命周期、磁盘缓存、按控件尺寸解码）
 */
object GlideUtils {

    private val placeholderDrawable by lazy { ColorDrawable(Color.parseColor("#F0F0F0")) }

    private fun baseOptions(defaultImg: Int): RequestOptions {
        var options = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .format(DecodeFormat.PREFER_RGB_565)
            .priority(Priority.NORMAL)
            .dontAnimate()
        options = if (defaultImg != 0) {
            options.placeholder(defaultImg).error(defaultImg)
        } else {
            options.placeholder(placeholderDrawable).error(placeholderDrawable)
        }
        return options
    }

    /**
     * 公共的加载网络图，直接加载图片地址（列表/封面推荐）
     */
    fun loadNormalImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        if (imageUrl.isBlank()) {
            clear(targetView, defaultImg)
            return
        }
        val options = baseOptions(defaultImg).transform(CenterCrop())
        Glide.with(targetView)
            .load(imageUrl)
            .apply(options)
            .thumbnail(0.1f)
            .into(targetView)
    }

    /**
     * 公共的加载网络图片的配置（想去旧 CDN 裁剪后缀再拼 .png）
     */
    fun loadImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        if (imageUrl.isBlank()) {
            clear(targetView, defaultImg)
            return
        }
        val image = if (imageUrl.length > 1) imageUrl.substring(0, imageUrl.length - 1) else imageUrl
        val options = baseOptions(defaultImg).transform(CenterCrop())
        Glide.with(targetView)
            .load("$image.png")
            .apply(options)
            .thumbnail(0.1f)
            .into(targetView)
    }

    /**
     * 按照图片原尺寸显示图片（详情长图）；仍按屏幕宽度解码，避免整图无压缩解码过慢
     */
    fun loadOriginalSizeImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        if (imageUrl.isBlank()) {
            clear(targetView, defaultImg)
            return
        }
        val transformation = CornerOriginSizeTransform(targetView.context, 10f)
        transformation.setExceptCorner(false, false, true, true)
        val screenW = targetView.resources.displayMetrics.widthPixels
        val options = baseOptions(defaultImg)
            .override(screenW, screenW * 3)
            .transform(transformation)
        Glide.with(targetView)
            .load(imageUrl)
            .apply(options)
            .into(targetView)
    }

    /**
     * 圆形图片（旧 CDN 裁剪后缀）
     */
    fun loadCircleImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        if (imageUrl.isBlank()) {
            clear(targetView, defaultImg)
            return
        }
        val image = if (imageUrl.length > 1) imageUrl.substring(0, imageUrl.length - 1) else imageUrl
        val options = baseOptions(defaultImg).transform(CircleCrop())
        Glide.with(targetView)
            .load("$image.png")
            .apply(options)
            .thumbnail(0.1f)
            .into(targetView)
    }

    /**
     * 圆形图片
     */
    fun loadNormalCircleImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        if (imageUrl.isBlank()) {
            clear(targetView, defaultImg)
            return
        }
        val options = baseOptions(defaultImg).transform(CircleCrop())
        Glide.with(targetView)
            .load(imageUrl)
            .apply(options)
            .thumbnail(0.1f)
            .into(targetView)
    }

    /**
     * 使用 Glide 加载 RelativeLayout、LinearLayout 等背景图片
     */
    fun loadBackgroudView(context: Context, imageUrl: String, targetView: View) {
        if (imageUrl.isBlank()) return
        Glide.with(context).asBitmap().load(imageUrl)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
            .into(object : SimpleTarget<Bitmap>(200, 200) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val drawable = BitmapDrawable(targetView.resources, resource)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        targetView.background = drawable
                    }
                }
            })
    }

    /** 预加载，用于 ViewPager/即将展示的封面 */
    fun preload(context: Context, imageUrl: String?) {
        if (imageUrl.isNullOrBlank()) return
        Glide.with(context.applicationContext)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .preload()
    }

    private fun clear(targetView: ImageView, defaultImg: Int) {
        Glide.with(targetView).clear(targetView)
        if (defaultImg != 0) {
            targetView.setImageResource(defaultImg)
        } else {
            targetView.setImageDrawable(placeholderDrawable)
        }
    }
}
