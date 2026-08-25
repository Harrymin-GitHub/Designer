package com.minhr.design.common_base.config.constants

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 广播常量
 */
class BroadCastConstant {
    companion object {
        @JvmField
        val LOGOUT = 1001
        @JvmField
        val LOGIN_SUCCESS = 1002

        //广播相对地址
        @JvmField
        val BROADCASE_ADDRESS = ".broadcast"
        @JvmField
        val BROADCASE_INTENT = ".intent"
    }
}