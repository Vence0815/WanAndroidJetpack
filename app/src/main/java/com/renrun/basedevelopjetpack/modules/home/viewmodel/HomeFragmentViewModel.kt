package com.renrun.basedevelopjetpack.modules.home.viewmodel

import android.text.TextUtils.isEmpty
import androidx.lifecycle.MutableLiveData
import com.renrun.basedevelopjetpack.base.BaseViewModel
import com.renrun.basedevelopjetpack.common.CommonLoadingState
import com.renrun.basedevelopjetpack.common.FreshStatus
import com.renrun.basedevelopjetpack.common.LoadingState
import com.renrun.basedevelopjetpack.data.Article
import com.renrun.basedevelopjetpack.data.ArticleResponseBody
import com.renrun.basedevelopjetpack.data.HomePageInfo
import com.renrun.basedevelopjetpack.modules.home.repository.FirstFragmentRepository
import com.renrun.basedevelopjetpack.modules.home.repository.HomeFragmentRepository
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by vence on 2018/12/25 16:40
 * 邮箱 ：vence0815@foxmail.com
 */
class HomeFragmentViewModel internal constructor(
    private val repository: HomeFragmentRepository
) : BaseViewModel() {

    private var page: Int = 1

    /**
     * 观察变化的数据
     */
    var reFreshData = MutableLiveData<List<Article>>()

    var loadMoreData = MutableLiveData<List<Article>>()


    /**
     * 下拉刷新
     */
    fun refreshData() {
        page = 1
        loadingLayout.value = CommonLoadingState(LoadingState.LOADING, FreshStatus.REFRESH, "")
        repository.getArticleList(page)
            .subscribeBy(
                onNext = {
                    loadingLayout.value = CommonLoadingState(LoadingState.SUCCESS, FreshStatus.REFRESH, "")
                    if (it.errorCode == 0) {
                        if (it.data.datas.isEmpty()) {
                            loadingLayout.value = CommonLoadingState(LoadingState.EMPTY, FreshStatus.REFRESH, "")
                        } else {
                            reFreshData.value = it.data.datas
                        }
                    } else {
                        loadingLayout.value = CommonLoadingState(LoadingState.ERROR, FreshStatus.REFRESH, it.errorMsg)
                    }
                },
                onError = {
                    loadingLayout.value =
                            CommonLoadingState(LoadingState.NETERROR, FreshStatus.REFRESH, it.message.toString())
                }
            )
            .addDispose()
    }

    /**
     * 上拉加载
     */
    fun loadMore() {
        page++
        loadingLayout.value = CommonLoadingState(LoadingState.LOADING, FreshStatus.LOADMORE, "")
        repository.getArticleList(page)
            .subscribeBy(
                onNext = {
                    loadingLayout.value = CommonLoadingState(LoadingState.SUCCESS, FreshStatus.LOADMORE, "")
                    if (it.errorCode==0) {
                        if (it.data.datas.isEmpty()) {
                            loadingLayout.value = CommonLoadingState(LoadingState.EMPTY, FreshStatus.LOADMORE, "")
                        } else {
                            loadMoreData.value = it.data.datas
                        }
                    } else {
                        loadingLayout.value = CommonLoadingState(LoadingState.ERROR, FreshStatus.LOADMORE, it.errorMsg)
                    }
                },
                onError = {
                    loadingLayout.value =
                            CommonLoadingState(LoadingState.NETERROR, FreshStatus.LOADMORE, it.message.toString())
                }
            )
            .addDispose()
    }

}