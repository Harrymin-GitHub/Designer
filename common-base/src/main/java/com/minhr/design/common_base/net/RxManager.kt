package com.minhr.design.common_base.net

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Observables 和 Subscribers管理,防止内存泄漏
 */
class RxManager {
    private val compositeDisposable = CompositeDisposable()

    /**
     * 添加observer
     * @param observer
     */
    fun addObserver(observer: DisposableObserver<*>?) {
        if (observer != null) {
            compositeDisposable.add(observer)
        }
    }

    fun clear() {
        compositeDisposable.dispose()
    }
}