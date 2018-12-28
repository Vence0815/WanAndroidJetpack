package com.renrun.basedevelopjetpack.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import com.renrun.basedevelopjetpack.common.CommonLoadingState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by vence on 2018/12/27 15:23
 * 邮箱 ：vence0815@foxmail.com
 */
open class BaseViewModel : ViewModel() {
    /**
     * 处理Rxjave的回调
     */
    private val disposes = CompositeDisposable()

    /**
     * 页面统一状态管理
     */
    val loadingLayout: MutableLiveData<CommonLoadingState> = MutableLiveData()

    override fun onCleared() {
        Logger.e("BaseViewModel---" + "onCleared() ")
        disposes.clear()
        super.onCleared()
    }

    protected fun Disposable.addDispose() {
        disposes.add(this)
    }
}