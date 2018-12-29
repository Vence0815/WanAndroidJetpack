package com.renrun.basedevelopjetpack.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.modules.home.adapter.HomeListsAdapter
import com.renrun.basedevelopjetpack.modules.home.viewmodel.HomeFragmentViewModel
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.youth.banner.Banner

/**
 * Created by vence on 2018/12/28 16:51
 * 邮箱 ：vence0815@foxmail.com
 */
/**
 * 加一个banner头部
 */
@BindingAdapter("addHead")
fun recycleViewAddHead(recyclerView: RecyclerView, adapter: HomeListsAdapter) {
    val header = LayoutInflater.from(recyclerView.context).inflate(R.layout.item_home_banner, null)
    adapter.removeAllHeaderView()
    adapter.addHeaderView(header)
}
