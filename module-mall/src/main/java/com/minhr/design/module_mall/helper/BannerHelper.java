package com.minhr.design.module_mall.helper;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.minhr.design.module_mall.adapter.BannerHolderView;

import java.util.List;

/**
 * author : Harrymin
 * e-mail : minhongrui@126.com
 * date   : 2026/8/25 7:38 PM
 * desc   :
 */
public class BannerHelper {
    public static void setBanner(ConvenientBanner banner, List<String> imgUrls){
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new BannerHolderView();
            }
        }, imgUrls);
    }
}
