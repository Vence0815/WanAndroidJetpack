package com.renrun.basedevelopjetpack.modules.project.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chad.library.adapter.base.BaseQuickAdapter
import com.classic.common.MultipleStatusView
import com.renrun.basedevelopjetpack.BR
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.base.BaseFragment
import com.renrun.basedevelopjetpack.common.FreshStatus
import com.renrun.basedevelopjetpack.common.LoadingState
import com.renrun.basedevelopjetpack.contants.CONTENT_CID_KEY
import com.renrun.basedevelopjetpack.data.Article
import com.renrun.basedevelopjetpack.data.Banner
import com.renrun.basedevelopjetpack.databinding.FragmentProjectListBinding
import com.renrun.basedevelopjetpack.ext.finishFresh
import com.renrun.basedevelopjetpack.ext.logD
import com.renrun.basedevelopjetpack.ext.logE
import com.renrun.basedevelopjetpack.ext.toastErrorWithicon
import com.renrun.basedevelopjetpack.modules.common.ArticleDetailActivity
import com.renrun.basedevelopjetpack.modules.home.adapter.HomeListsAdapter
import com.renrun.basedevelopjetpack.modules.home.viewmodel.HomeFragmentViewModel
import com.renrun.basedevelopjetpack.modules.project.adapter.ProjectListAdapter
import com.renrun.basedevelopjetpack.modules.project.adapter.ProjectPagerAdapter
import com.renrun.basedevelopjetpack.modules.project.viewmodel.ProjectFragmentViewModel
import com.renrun.basedevelopjetpack.utils.InjectUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout


/**
 * Created by chenxz on 2018/5/20.
 */
class ProjectListFragment : BaseFragment<FragmentProjectListBinding>() {

    private var listData: List<Article> = mutableListOf()
    lateinit var adapter: ProjectListAdapter

    companion object {
        fun getInstance(cid: Int): ProjectListFragment {
            val fragment = ProjectListFragment()
            val args = Bundle()
            args.putInt(CONTENT_CID_KEY, cid)
            fragment.arguments = args
            return fragment
        }
    }

    override val layoutId: Int = R.layout.fragment_project_list

    override fun initView() {
        binding.apply {
            val factory = InjectUtils.provideProjectFragmentViewModelFactory(arguments!!.getInt(CONTENT_CID_KEY))
            val projectViewModel =
                ViewModelProviders.of(this@ProjectListFragment, factory).get(ProjectFragmentViewModel::class.java)
            //viewmodel中数据绑定
            setVariable(BR.viewModel, projectViewModel)
            projectViewModel.refreshData()
            //RecycleView 的adapter相关
            adapter = ProjectListAdapter(listData)
            recycleView.adapter = adapter
            adapter.bindToRecyclerView(recycleView)
            subscribeUi(projectViewModel, refreshView, statusView)
            addLinsterner()
        }
    }


    fun addLinsterner() {
        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { it, view, position ->
            val intent = Intent(activity, ArticleDetailActivity::class.java)
            intent.putExtra("link", listData.get(position).link)
            intent.putExtra("title", listData.get(position).title)
            startActivity(intent)
            logE("点击了---->" + position)
        }
    }

    fun subscribeUi(
        viewModel: ProjectFragmentViewModel,
        refreshView: SmartRefreshLayout,
        statusView: MultipleStatusView
    ) {
        //todo  分开感觉实现起来并不优雅，还有封装的空间
        //LiveData监听数据变化
        viewModel.reFreshData.observe(viewLifecycleOwner, Observer {
            logE("我收到数据变化啦！！！！！！---" + it.size)
            listData = it
            adapter.replaceData(listData)

        })
        viewModel.loadMoreData.observe(viewLifecycleOwner, Observer {
            logE("我收到数据变化啦！！！！！！---" + it.size)
            listData = it
            adapter.addData(listData)
        })

        /**
         * 页面状态统一管理
         */
        viewModel.loadingLayout.observe(viewLifecycleOwner, Observer {
            logE("我收到状态变化啦！！！！！！---" + it.loadingState + "--" + it.freshStatus + "-----" + it.errorMsg)
            when (it.loadingState) {
                LoadingState.ERROR -> {
                    toastErrorWithicon(it.errorMsg)
                    statusView.showError()
                }
                LoadingState.EMPTY -> {
                    if (it.freshStatus == FreshStatus.REFRESH) {
                        refreshView.setNoMoreData(false)
                        statusView.showEmpty()
                    } else {
                        refreshView.setNoMoreData(true)
                    }
                }
                LoadingState.LOADING -> logD("正在加载")
                LoadingState.NETERROR -> {
                    toastErrorWithicon(it.errorMsg)
                    statusView.showNoNetwork()
                }
                LoadingState.SUCCESS -> {
                    finishFresh(refreshView)
                    statusView.showContent()
                }
            }
        })

    }
}