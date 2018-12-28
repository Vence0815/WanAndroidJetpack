package com.renrun.basedevelopjetpack.modules.home.repository

import com.renrun.basedevelopjetpack.data.ArticleResponseBody
import com.renrun.basedevelopjetpack.data.HomePageInfo
import com.renrun.basedevelopjetpack.data.HttpResult
import com.renrun.basedevelopjetpack.ext.dispatchDefault
import com.renrun.basedevelopjetpack.http.ApiClient
import io.reactivex.Observable

/**
 * Created by vence on 2018/12/25 16:47
 * 邮箱 ：vence0815@foxmail.com
 * 数据获取类
 */
class HomeFragmentRepository {

    //双重校验，可以在这给单例加属性
    companion object {
        @Volatile
        private var instance: HomeFragmentRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: HomeFragmentRepository().also { instance = it }
            }
    }

    /**
     * 获取首页文章列表
     */
    fun getArticleList(page: Int): Observable<HttpResult<ArticleResponseBody>> {
        return ApiClient.instance.api.getArticles(page)
            .dispatchDefault()
    }


}