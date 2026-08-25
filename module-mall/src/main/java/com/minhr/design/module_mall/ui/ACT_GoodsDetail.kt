package com.minhr.design.module_mall.ui

import android.graphics.Color
import android.os.Build
import androidx.fragment.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.bigkoo.convenientbanner.ConvenientBanner
import com.minhr.design.common_base.BaseAppliction
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_base.dagger.mvp.BaseActivity
import com.minhr.design.common_base.utils.StatusBarHelper
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.common_ui.view.IOSScrollView
import com.minhr.design.common_ui.view.OverTextView
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.bean.CommentBean
import com.minhr.design.module_mall.bean.GoodsContentBean
import com.minhr.design.module_mall.bean.GoodsDesBean
import com.minhr.design.module_mall.bean.RevelentBean
import com.minhr.design.module_mall.dagger2.DaggerMallComponent
import com.minhr.design.module_mall.helper.BannerHelper
import com.minhr.design.module_mall.mvp.contract.GoodsContract
import com.minhr.design.module_mall.mvp.model.GoodsModel
import com.minhr.design.module_mall.mvp.presenter.GoodsPresenter
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent
import org.greenrobot.eventbus.EventBus

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品详情页面
 */
@Route(path = ARouterConfig.ACT_GoodsDetail)
class ACT_GoodsDetail : BaseActivity<GoodsPresenter, GoodsModel>(), GoodsContract.View {
    private val banner by bindView<ConvenientBanner<String>>(R.id.banner)
    private val layout_title_root by bindView<ConstraintLayout>(R.id.layout_title_root)
    private val sus_title by bindView<View>(R.id.sus_title)
    private val tv_product_des by bindView<TextView>(R.id.tv_product_des)
    private val tv_comment by bindView<TextView>(R.id.tv_comment)
    private val line_bottom_two by bindView<View>(R.id.line_bottom_two)
    private val line_bottom_one by bindView<View>(R.id.line_bottom_one)
    private val tv_watch_more by bindView<TextView>(R.id.tv_watch_more)
    private val tv_brandStory by bindView<OverTextView>(R.id.tv_brandStory)
    private val view_divider by bindView<View>(R.id.view_divider)
    private val scrollView by bindView<IOSScrollView>(R.id.scrollView)
    private val iv_back by bindView<ImageView>(R.id.iv_back)
    private val iv_cart by bindView<ImageView>(R.id.iv_cart)
    private val iv_share by bindView<ImageView>(R.id.iv_share)
    private val tv_goods_title by bindView<TextView>(R.id.tv_goods_title)
    private val tv_price by bindView<TextView>(R.id.tv_price)
    private val tv_productUser by bindView<TextView>(R.id.tv_productUser)
    private val tv_brand by bindView<TextView>(R.id.tv_brand)
    private val tv_favNum by bindView<TextView>(R.id.tv_favNum)
    private val tv_like by bindView<TextView>(R.id.tv_like)
    private val tv_productDiscountTxt by bindView<TextView>(R.id.tv_productDiscountTxt)
    private val tv_desc by bindView<TextView>(R.id.tv_desc)
    private val tv_postage by bindView<TextView>(R.id.tv_postage)
    private val iv_productUser by bindView<ImageView>(R.id.iv_productUser)
    private val iv_brand by bindView<ImageView>(R.id.iv_brand)
    var productId: String? = "3115460"
    var currentFragment: Fragment? = null
    var fragmentGoodsDes: FRA_GoodsDes? = null
    var fragmentGoodsCom: FRA_GoodsComment? = null
    override fun getContentViewLayoutId(): Int = R.layout.act_goods_detail

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerMallComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startEvents() {
        StatusBarHelper.setStatusTextColor(true, this)
        productId = intent.extras?.getString(ARouterConstants.PRODUCT_ID).orEmpty()
        mPresenter?.getGoodsContent(productId.orEmpty())
        mPresenter?.getGoodsDescription(productId.orEmpty())
        mPresenter?.getGoodsCommentList(productId.orEmpty(), 0)
//        mPresenter?.getRevelentGoodsList(productId!!)
        val currentTimeMillis = System.currentTimeMillis()
        Log.e("TAG", "currentTimeMillis=" + currentTimeMillis)


        initView()

        initFragments()

        initListener()
    }

    private fun initView() {

        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
        banner.setPageIndicator(intArrayOf(R.drawable.bg_shape_circle_grey, R.drawable.bg_shape_circle_white))
        banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)

        layout_title_root.post {
            Log.e("TAG", "layout_title_root.height=" + layout_title_root.height)
            sus_title.layoutParams.height = layout_title_root.height
        }

    }

    private fun initFragments() {
        tv_product_des.isSelected = true
        fragmentGoodsDes = FRA_GoodsDes()
        fragmentGoodsCom = FRA_GoodsComment()
        if (!fragmentGoodsDes!!.isAdded) {
            currentFragment = fragmentGoodsDes
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fl_content, fragmentGoodsDes!!)
            transaction.commitAllowingStateLoss()
        }
    }

    /**
     * 选中评价
     */
    private fun checkGoodsCom() {
        line_bottom_two.visibility = View.VISIBLE
        line_bottom_one.visibility = View.INVISIBLE
        tv_comment.isSelected = true
        tv_product_des.isSelected = false
        switchContent(currentFragment!!, fragmentGoodsCom!!)
    }

    /**
     * 选中商品描述
     */
    private fun checkGoodsDes() {
        line_bottom_one.visibility = View.VISIBLE
        line_bottom_two.visibility = View.INVISIBLE
        tv_product_des.isSelected = true
        tv_comment.isSelected = false
        switchContent(currentFragment!!, fragmentGoodsDes!!)
    }

    /**
     * 切换Fragment
     *
     * @param from
     * @param to
     */
    fun switchContent(from: Fragment, to: Fragment) {
        if (currentFragment !== to) {
            currentFragment = to
            val transaction = supportFragmentManager.beginTransaction()
            if (!to.isAdded) { // 先判断是否被add过
                transaction.hide(from).add(R.id.fl_content, to).commitAllowingStateLoss() // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss() // 隐藏当前的fragment，显示下一个
            }
        }
    }

    private fun initListener() {
        tv_product_des.setOnClickListener { checkGoodsDes() }
        tv_comment.setOnClickListener { checkGoodsCom() }

        tv_watch_more.setOnClickListener {
            // android:lines 会同时锁死 min/max；需清掉限制才能展开全文
            tv_brandStory.ellipsize = null
            tv_brandStory.minLines = 0
            tv_brandStory.maxLines = Integer.MAX_VALUE
            tv_watch_more.visibility = View.GONE
        }

        //默认设置标题栏透明
        layout_title_root.setBackgroundColor(Color.argb(0, 255, 255, 255))
        view_divider.visibility = View.INVISIBLE

        scrollView.setOnScrollViewListener { scrollX, scrollY, oldx, oldScrollY ->
            var distanceScrollY = banner.height - layout_title_root.height

            if (scrollY <= 0) {//未滑动:设置全透明
                layout_title_root.setBackgroundColor(Color.argb(0, 255, 255, 255))
                iv_back.setImageResource(R.drawable.ic_back_arrow_white)
                iv_cart.setImageResource(R.drawable.ic_cart_white)
                iv_share.setImageResource(R.drawable.ic_share_white)
                view_divider.visibility = View.INVISIBLE

            } else if (scrollY in 1..distanceScrollY) { //滑动过程中 并且在mHeight之内
                val scale = scrollY.toFloat() / distanceScrollY
                val alpha = 255 * scale
                //白色
                layout_title_root.setBackgroundColor(Color.argb(alpha.toInt(), 255, 255, 255))
//                view_circle_one.setBackgroundColor(Color.argb(alpha.toInt(), 59, 59, 59))
//                view_circle_two.setBackgroundColor(Color.argb(alpha.toInt(), 59, 59, 59))
//                view_circle_three.setBackgroundColor( Color.argb(alpha.toInt(), 59, 59, 59))
                if (scale >= 0.5) {
                    iv_back.setImageResource(R.drawable.ic_back_arrow_black)
                    iv_cart.setImageResource(R.drawable.ic_cart_black)
                    iv_share.setImageResource(R.drawable.ic_share_black)
                } else {
                    iv_back.setImageResource(R.drawable.ic_back_arrow_white)
                    iv_cart.setImageResource(R.drawable.ic_cart_white)
                    iv_share.setImageResource(R.drawable.ic_share_white)
                }
                view_divider.visibility = View.INVISIBLE
            } else {//超过mHeight
                layout_title_root.setBackgroundColor(Color.argb(255, 255, 255, 255))
                iv_back.setImageResource(R.drawable.ic_back_arrow_black)
                iv_cart.setImageResource(R.drawable.ic_cart_black)
                iv_share.setImageResource(R.drawable.ic_share_black)
                view_divider.visibility = View.VISIBLE
            }
        }
    }

    override fun getGoodsContent(dataBean: GoodsContentBean) {
        Log.e("TAG", "getGoodsContent=" + dataBean.brand)
        tv_goods_title.text = dataBean.title
        tv_brandStory.text = dataBean?.brandStory
        tv_price.text = "￥" + dataBean?.price
        tv_productUser.text = "主理人：" + dataBean?.productUser
        tv_brand.text = "品牌：" + dataBean?.brand
        tv_favNum.text = dataBean?.favNum
        tv_like.text = "喜欢 " + dataBean?.favNum
        tv_productDiscountTxt.text = dataBean?.productDiscountTxt
        tv_desc.text = dataBean?.platFormWeixin?.desc
        if (!TextUtils.isEmpty(dataBean?.postage)) tv_postage.text = "邮费：" + dataBean?.postage + "元" else tv_postage.text = "包邮"

        GlideUtils.loadNormalCircleImage(dataBean.avaPath.orEmpty(), iv_productUser, 0)
        GlideUtils.loadNormalImage(dataBean.brandIcon.orEmpty(), iv_brand, 0)

        // listOf 不是 ArrayList，强转会 ClassCastException
        val bannerList = ArrayList(dataBean.imgsUrlList)
        bannerList.add(0, dataBean.image.orEmpty())
        BannerHelper.setBanner(banner, bannerList)

        tv_brandStory.viewTreeObserver.addOnPreDrawListener((object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                //这个回调会调用多次，获取完行数记得注销监听
                tv_brandStory.viewTreeObserver.removeOnPreDrawListener(this)
                val layout = tv_brandStory.layout
                val ellipsized = layout != null && (0 until layout.lineCount).any { layout.getEllipsisCount(it) > 0 }
                tv_watch_more.visibility =
                    if (ellipsized || exceedsThreeLines(tv_brandStory)) View.VISIBLE else View.GONE
                return false
            }

        }))
    }

    /** 不受 maxLines 限制时，文本实际是否超过 3 行 */
    private fun exceedsThreeLines(tv: TextView): Boolean {
        val text = tv.text ?: return false
        if (text.isEmpty()) return false
        val availableWidth = tv.width - tv.paddingLeft - tv.paddingRight
        if (availableWidth <= 0) return false
        val measured = android.text.StaticLayout.Builder
            .obtain(text, 0, text.length, tv.paint, availableWidth)
            .setAlignment(android.text.Layout.Alignment.ALIGN_NORMAL)
            .setLineSpacing(tv.lineSpacingExtra, tv.lineSpacingMultiplier)
            .setIncludePad(tv.includeFontPadding)
            .build()
        return measured.lineCount > 3
    }

    override fun getGoodsDescription(dataList: List<GoodsDesBean>) {

//        GlideUtils.loadNormalImage(dataList[1].content, iv_bottom, 0)
        Log.e("TAG", "getGoodsDescription=" + dataList.size)
        EventBus.getDefault().post(dataList)
    }

    override fun getGoodsCommentList(dataList: List<CommentBean>) {
        Log.e("TAG", "getGoodsCommentList=" + dataList.size)
    }

    override fun getRevelentGoodsList(dataBean: RevelentBean) {
        Log.e("TAG", "getRevelentGoodsList=" + dataBean.revelentList.size)
    }


}