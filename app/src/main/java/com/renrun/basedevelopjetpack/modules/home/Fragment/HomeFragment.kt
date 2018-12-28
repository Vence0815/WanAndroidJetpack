package com.renrun.basedevelopjetpack.modules.home.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.renrun.basedevelopjetpack.BR
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.base.BaseFragment
import com.renrun.basedevelopjetpack.common.FreshStatus
import com.renrun.basedevelopjetpack.common.LoadingState
import com.renrun.basedevelopjetpack.data.Article
import com.renrun.basedevelopjetpack.databinding.FragmentHomeBinding
import com.renrun.basedevelopjetpack.ext.finishFresh
import com.renrun.basedevelopjetpack.ext.logE
import com.renrun.basedevelopjetpack.ext.logW
import com.renrun.basedevelopjetpack.ext.toastErrorWithicon
import com.renrun.basedevelopjetpack.modules.home.adapter.HomeListsAdapter
import com.renrun.basedevelopjetpack.modules.home.viewmodel.HomeFragmentViewModel
import com.renrun.basedevelopjetpack.utils.InjectUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by vence on 2018/12/28 14:02
 * 邮箱 ：vence0815@foxmail.com
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var lisData: List<Article> = mutableListOf()
    lateinit var adapter: HomeListsAdapter

    override val layoutId: Int = R.layout.fragment_home


    override fun initView() {
        binding.apply {
            val factory = InjectUtils.provideHomeFragmentViewModelFactory()
            val viewModel = ViewModelProviders.of(this@HomeFragment, factory)
                .get(HomeFragmentViewModel::class.java)
            //viewmodel中数据绑定
            setVariable(BR.viewModel, viewModel)
            //RecycleView 的adapter相关
            adapter = HomeListsAdapter(R.layout.item_home_lists, lisData)
            recycleView.adapter = adapter
            adapter.bindToRecyclerView(recycleView)
            subscribeUi(viewModel, refreshView)
            //进来第一次初始化数据
            viewModel.refreshData()
        }
    }


    fun subscribeUi(viewModel: HomeFragmentViewModel, refreshView: SmartRefreshLayout) {
        //todo  分开感觉实现起来并不优雅，还有封装的空间
        //LiveData监听数据变化
        viewModel.reFreshData.observe(viewLifecycleOwner, Observer {
            logE("我收到数据变化啦！！！！！！---" + it.size)
            lisData = it
            adapter.replaceData(lisData)

        })
        viewModel.loadMoreData.observe(viewLifecycleOwner, Observer {
            logE("我收到数据变化啦！！！！！！---" + it.size)
            lisData = it
            adapter.addData(lisData)
        })

        /**
         * 页面状态统一管理
         */
        viewModel.loadingLayout.observe(viewLifecycleOwner, Observer {
            logE("我收到状态变化啦！！！！！！---" + it.loadingState + "--" + it.freshStatus + "-----" + it.errorMsg)
            when (it.loadingState) {
                LoadingState.ERROR -> toastErrorWithicon(it.errorMsg)
                LoadingState.EMPTY -> {
                    if (it.freshStatus == FreshStatus.REFRESH) {
                        refreshView.setNoMoreData(false)
                        adapter.setEmptyView(R.layout.empty_usual_view, llContent)
                    } else {
                        refreshView.setNoMoreData(true)
                    }
                }
                LoadingState.LOADING -> logW("正在加载")
                LoadingState.NETERROR -> toastErrorWithicon(it.errorMsg)
                LoadingState.SUCCESS -> finishFresh(refreshView)
            }
        })
    }

}