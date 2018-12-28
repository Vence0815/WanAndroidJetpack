package com.renrun.basedevelopjetpack.adapter.databingadapter

import androidx.databinding.BindingAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

/**
 * Created by vence on 2018/12/26 17:53
 * 邮箱 ：vence0815@foxmail.com
 */
/**
 * 下拉刷新
 * TODO 两种方法都可以，还不太清楚这个原理要研究一下
 */
@BindingAdapter("bindRefresh")
fun setBindRefresh(smartRefreshLayout: SmartRefreshLayout, refreshListener: OnRefreshListener) {
    smartRefreshLayout.setOnRefreshListener(refreshListener)
}

//@BindingAdapter("bindRefresh")
//fun setBindRefresh(smartRefreshLayout: SmartRefreshLayout,refreshListener: OnRefreshListener) {
//    smartRefreshLayout.setOnRefreshListener {
//        refreshListener.onRefresh(smartRefreshLayout)
//    }
//}

/**
 * 上拉加载
 */
@BindingAdapter("bindLoadMore")
fun setLoadMore(smartRefreshLayout: SmartRefreshLayout, loadMoreListener: OnLoadMoreListener) {
    smartRefreshLayout.setOnLoadMoreListener(loadMoreListener)
}