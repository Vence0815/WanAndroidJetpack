package com.renrun.basedevelopjetpack.modules.project.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.renrun.basedevelopjetpack.BR
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.data.Article

/**
 * Created by vence on 2018/12/26 15:49
 * 邮箱 ：vence0815@foxmail.com
 */
class ProjectListAdapter(data: List<Article>) :
    BaseQuickAdapter<Article, ProjectListAdapter.HomeViewHolder>(R.layout.item_project_lists, data) {

    override fun convert(helper: HomeViewHolder?, item: Article?) {
        val binding = helper?.binding
        binding?.setVariable(BR.articleData, item)
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