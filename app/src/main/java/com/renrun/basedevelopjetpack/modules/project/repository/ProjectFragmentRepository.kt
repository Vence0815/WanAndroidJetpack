package com.renrun.basedevelopjetpack.modules.project.repository

import com.renrun.basedevelopjetpack.data.ArticleResponseBody
import com.renrun.basedevelopjetpack.data.Banner
import com.renrun.basedevelopjetpack.data.HttpResult
import com.renrun.basedevelopjetpack.data.ProjectTreeBean
import com.renrun.basedevelopjetpack.ext.dispatchDefault
import com.renrun.basedevelopjetpack.http.ApiClient
import io.reactivex.Observable

/**
 * Created by vence on 2018/12/25 16:47
 * 邮箱 ：vence0815@foxmail.com
 * 数据获取类
 */
class ProjectFragmentRepository {

    //双重校验，可以在这给单例加属性
    companion object {
        @Volatile
        private var instance: ProjectFragmentRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: ProjectFragmentRepository().also { instance = it }
            }
    }

    /**
     * 获取项目分类
     */
    fun getProjectTab(): Observable<HttpResult<List<ProjectTreeBean>>> {
        return ApiClient.instance.api.getProjectTree()
            .dispatchDefault()
    }

    /**
     *获取项目分类下的具体内容
     */
    fun getProjectList(page: Int, cid: Int): Observable<HttpResult<ArticleResponseBody>> {
        return ApiClient.instance.api.getProjectList(page, cid)
            .dispatchDefault()
    }


}