package com.renrun.basedevelopjetpack.adapter.recycleadapter

import android.graphics.Movie
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.renrun.basedevelopjetpack.BR
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.data.HomePageInfo

/**
 * Created by vence on 2018/12/26 15:49
 * 邮箱 ：vence0815@foxmail.com
 */
class HomeListAdapter(layoutResId: Int, data: List<HomePageInfo.RecordsEntity>) :
    BaseQuickAdapter<HomePageInfo.RecordsEntity, HomeListAdapter.HomeViewHolder>(layoutResId, data) {

    override fun convert(helper: HomeViewHolder?, item: HomePageInfo.RecordsEntity?) {
        val binding = helper?.binding
        binding?.setVariable(BR.homeData, item)
        binding?.executePendingBindings()
    }

    //这个可以固定写法了可以尝试封住成基类调用
    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
            ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }

    inner class HomeViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ViewDataBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
    }

}