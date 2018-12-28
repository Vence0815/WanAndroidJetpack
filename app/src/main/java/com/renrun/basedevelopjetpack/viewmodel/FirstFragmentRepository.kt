package com.renrun.basedevelopjetpack.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renrun.basedevelopjetpack.data.HomePageInfo
import com.renrun.basedevelopjetpack.ext.dispatchDefault
import com.renrun.basedevelopjetpack.http.ApiClient
import com.renrun.basedevelopjetpack.utils.RxUtil
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*

/**
 * Created by vence on 2018/12/25 16:47
 * 邮箱 ：vence0815@foxmail.com
 * 数据获取类
 */
class FirstFragmentRepository {

    //双重校验，可以在这给单例加属性
    companion object {
        @Volatile
        private var instance: FirstFragmentRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FirstFragmentRepository().also { instance = it }
            }
    }


    fun getHomeList(page: String): Observable<HomePageInfo> {
        val map = hashMapOf<String, String>()
        map["current"] = page
        map["size"] = "20"
        return ApiClient.instance.api.homeList("1", map)
            .dispatchDefault()
    }


}