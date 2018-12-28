package com.renrun.basedevelopjetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.renrun.basedevelopjetpack.base.BaseViewModel
import com.renrun.basedevelopjetpack.common.CommonLoadingState
import com.renrun.basedevelopjetpack.common.FreshStatus
import com.renrun.basedevelopjetpack.common.LoadingState
import com.renrun.basedevelopjetpack.data.HomePageInfo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by vence on 2018/12/25 16:40
 * 邮箱 ：vence0815@foxmail.com
 */
class FirstFragmentViewModel internal constructor(
    private val repository: FirstFragmentRepository
) : BaseViewModel() {

    private var page: Int = 1

    /**
     * 观察变化的数据
     */
    var reFreshData = MutableLiveData<List<HomePageInfo.RecordsEntity>>()

    var loadMoreData = MutableLiveData<List<HomePageInfo.RecordsEntity>>()



    /**
     * 下拉刷新
     */
    fun refreshData() {
        page = 1
        loadingLayout.value = CommonLoadingState(LoadingState.LOADING, FreshStatus.REFRESH, "")
        repository.getHomeList(page.toString())
            .subscribeBy(
                onNext = {
                    loadingLayout.value = CommonLoadingState(LoadingState.SUCCESS, FreshStatus.REFRESH, "")
                    if (it.isResponseSuccess) {
                        if (it.records.isEmpty()) {
                            loadingLayout.value = CommonLoadingState(LoadingState.EMPTY, FreshStatus.REFRESH, "")
                        } else {
                            reFreshData.value = it.records
                        }
                    } else {
                        loadingLayout.value = CommonLoadingState(LoadingState.ERROR, FreshStatus.REFRESH, it.message)
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
        repository.getHomeList(page.toString())
            .subscribeBy(
                onNext = {
                    loadingLayout.value = CommonLoadingState(LoadingState.SUCCESS, FreshStatus.LOADMORE, "")
                    if (it.isResponseSuccess) {
                        if (it.records.isEmpty()) {
                            loadingLayout.value = CommonLoadingState(LoadingState.EMPTY, FreshStatus.LOADMORE, "")
                        } else {
                            loadMoreData.value = it.records
                        }
                    } else {
                        loadingLayout.value = CommonLoadingState(LoadingState.ERROR, FreshStatus.LOADMORE, it.message)
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