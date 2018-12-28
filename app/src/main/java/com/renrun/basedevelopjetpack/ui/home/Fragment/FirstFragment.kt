package com.renrun.basedevelopjetpack.ui.home.Fragment

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.renrun.basedevelopjetpack.BR
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.adapter.recycleadapter.HomeListAdapter
import com.renrun.basedevelopjetpack.base.BaseFragment
import com.renrun.basedevelopjetpack.common.FreshStatus
import com.renrun.basedevelopjetpack.common.LoadingState
import com.renrun.basedevelopjetpack.data.HomePageInfo
import com.renrun.basedevelopjetpack.databinding.FragmentFirstBinding
import com.renrun.basedevelopjetpack.ext.*
import com.renrun.basedevelopjetpack.ui.home.viewmodel.FirstFragmentViewModel
import com.renrun.basedevelopjetpack.utils.InjectUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by vence on 2018/12/20 16:51
 * 邮箱 ：vence0815@foxmail.com
 */
class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    private var lisData: List<HomePageInfo.RecordsEntity> = mutableListOf()
    lateinit var adapter: HomeListAdapter

    override val layoutId: Int = R.layout.fragment_first

    override fun initView() {
        logI("FirstFragment------" + "initView")
        binding.apply {
            val factory = InjectUtils.provideFirstFragmentViewModelFactory()
            val viewModel = ViewModelProviders.of(this@FirstFragment, factory)
                .get(FirstFragmentViewModel::class.java)
            //viewmodel中数据绑定
            setVariable(BR.viewModel, viewModel)
            //RecycleView 的adapter相关
            adapter = HomeListAdapter(R.layout.item_home_list, lisData)
            recycleView.adapter = adapter
            adapter.bindToRecyclerView(recycleView)
            subscribeUi(viewModel, refreshView)
            //进来第一次初始化数据
            viewModel.refreshData()
        }
    }

    fun subscribeUi(viewModel: FirstFragmentViewModel, refreshView: SmartRefreshLayout) {
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


    override fun onAttach(context: Context) {
        super.onAttach(context)
        logI("FirstFragment------" + "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI("FirstFragment------" + "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logI("FirstFragment------" + "onActivityCreated")
    }


    override fun onResume() {
        super.onResume()
        logI("FirstFragment------" + "onResume")
    }

    override fun onPause() {
        super.onPause()
        logI("FirstFragment------" + "onPause")
    }


    override fun onStart() {
        super.onStart()
        logI("FirstFragment------" + "onStart")
    }

    override fun onStop() {
        super.onStop()
        logI("FirstFragment------" + "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logI("FirstFragment------" + "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logI("FirstFragment------" + "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        logI("FirstFragment------" + "onDetach")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        logE("FirstFragment-----$isVisibleToUser")
    }
}