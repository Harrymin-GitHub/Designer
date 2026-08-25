package com.minhr.design.common_base.net

import android.net.ParseException
import com.google.gson.JsonParseException
import com.minhr.design.common_base.utils.NetUtils
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 * Created by Harrymin on 2018/4/1.
 * e-mail : minhongrui@126.com
 * description：网络错误code处理异常类
 */
class RetrofitException {

    class ResponseThrowable : Exception() {
        var code: Int = 0
        override var message: String? = null
    }

    inner class ServerException : RuntimeException() {
        var code: Int = 0
        override var message: String? = null
    }

    companion object {
        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
        private const val INTERNAL_SERVER_ERROR = 500
        private const val BAD_GATEWAY = 502
        private const val SERVICE_UNAVAILABLE = 503
        private const val GATEWAY_TIMEOUT = 504

        const val UNKNOWN = 1000
        const val PARSE_ERROR = 1001
        const val NETWORD_ERROR = 1002
        const val HTTP_ERROR = 1003
        const val SSL_ERROR = 1005
        const val HOST_ERROR = 1006

        fun getResponseThrowable(e: Throwable): ResponseThrowable {
            val ex = ResponseThrowable()
            when (e) {
                is HttpException -> {
                    ex.code = HTTP_ERROR
                    ex.message = when (e.code()) {
                        UNAUTHORIZED, FORBIDDEN -> "请检查权限"
                        NOT_FOUND -> "接口不存在或已下线"
                        REQUEST_TIMEOUT, GATEWAY_TIMEOUT -> "请求超时，请稍后重试"
                        INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> "服务暂时不可用"
                        else -> "请求失败(${e.code()})"
                    }
                }
                is ServerException -> {
                    ex.code = e.code
                    ex.message = e.message
                }
                is JsonParseException, is JSONException, is ParseException -> {
                    ex.code = PARSE_ERROR
                    ex.message = "数据解析错误"
                }
                is UnknownHostException -> {
                    ex.code = HOST_ERROR
                    ex.message = if (NetUtils.isNetworkConnected) {
                        "服务器地址不可达，接口可能已下线"
                    } else {
                        "请检查你的网络连接"
                    }
                }
                is SocketTimeoutException -> {
                    ex.code = NETWORD_ERROR
                    ex.message = "连接超时，请稍后重试"
                }
                is ConnectException -> {
                    ex.code = NETWORD_ERROR
                    ex.message = if (NetUtils.isNetworkConnected) {
                        "无法连接服务器"
                    } else {
                        "请检查你的网络连接"
                    }
                }
                is SSLHandshakeException -> {
                    ex.code = SSL_ERROR
                    ex.message = "证书验证失败"
                }
                is IOException -> {
                    ex.code = NETWORD_ERROR
                    ex.message = if (NetUtils.isNetworkConnected) {
                        "网络请求失败，请稍后重试"
                    } else {
                        "请检查你的网络连接"
                    }
                }
                else -> {
                    ex.code = UNKNOWN
                    ex.message = if (NetUtils.isNetworkConnected) {
                        "请求失败：${e.javaClass.simpleName}"
                    } else {
                        "请检查你的网络连接"
                    }
                }
            }
            return ex
        }
    }
}
