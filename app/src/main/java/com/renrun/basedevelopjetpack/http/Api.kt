package com.renrun.basedevelopjetpack.http

import com.renrun.basedevelopjetpack.data.ArticleResponseBody
import com.renrun.basedevelopjetpack.data.Banner
import com.renrun.basedevelopjetpack.data.HttpResult
import com.renrun.basedevelopjetpack.data.ProjectTreeBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by vence on 2018/12/25 14:42
 * 邮箱 ：vence0815@foxmail.com
 * 网络请求统一管理
 */
interface Api {

    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    fun getBanners(): Observable<HttpResult<List<Banner>>>

    /**
     * 获取首页文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    fun getArticles(@Path("pageNum") pageNum: Int): Observable<HttpResult<ArticleResponseBody>>

    /**
     * 项目数据
     * http://www.wanandroid.com/project/tree/json
     */
    @GET("project/tree/json")
    fun getProjectTree(): Observable<HttpResult<List<ProjectTreeBean>>>

    /**
     * 项目列表数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * @param page
     * @param cid
     */
    @GET("project/list/{page}/json")
    fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<HttpResult<ArticleResponseBody>>

}