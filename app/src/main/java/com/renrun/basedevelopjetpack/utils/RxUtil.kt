package com.renrun.basedevelopjetpack.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by vence on 2018/11/8 15:39
 * 邮箱 ：vence0815@foxmail.com
 */
object RxUtil {

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    fun <T> ioToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T>() {
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}