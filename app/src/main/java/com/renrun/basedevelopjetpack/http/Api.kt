package com.renrun.basedevelopjetpack.http

import com.renrun.basedevelopjetpack.data.HomePageInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

/**
 * Created by vence on 2018/12/25 14:42
 * 邮箱 ：vence0815@foxmail.com
 * 网络请求统一管理
 */
interface Api {
    /**
     * 首页列表接口
     */
    @GET("appns/goodsCommon/list")
    fun homeList(
        @Header("Authorization") sessiond: String,
        @QueryMap map: Map<String, String>
    ): Observable<HomePageInfo>
}