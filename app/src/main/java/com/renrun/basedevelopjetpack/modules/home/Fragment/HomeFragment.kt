package com.renrun.basedevelopjetpack.modules.home.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.classic.common.MultipleStatusView
import com.renrun.basedevelopjetpack.BR
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.base.BaseFragment
import com.renrun.basedevelopjetpack.common.FreshStatus
import com.renrun.basedevelopjetpack.common.LoadingState
import com.renrun.basedevelopjetpack.data.Article
import com.renrun.basedevelopjetpack.data.Banner
import com.renrun.basedevelopjetpack.databinding.FragmentHomeBinding
import com.renrun.basedevelopjetpack.ext.*
import com.renrun.basedevelopjetpack.modules.home.adapter.HomeListsAdapter
import com.renrun.basedevelopjetpack.modules.home.viewmodel.HomeFragmentViewModel
import com.renrun.basedevelopjetpack.utils.GlideImageLoader
import com.renrun.basedevelopjetpack.utils.InjectUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer

/**
 * Created by vence on 2018/12/28 14:02
 * 邮箱 ：vence0815@foxmail.com
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var lisData: List<Article> = mutableListOf()
    lateinit var adapter: HomeListsAdapter

    private var bannerUrl: MutableList<String> = mutableListOf()
    private var bannerTitile: MutableList<String> = mutableListOf()

    /**
     * banner view
     */
    private var bannerView: com.youth.banner.Banner? = null

    override val layoutId: Int = R.layout.fragment_home

    override fun initView() {
        binding.apply {
            val factory = InjectUtils.provideHomeFragmentViewModelFactory()
            val homeViewModel = ViewModelProviders.of(this@HomeFragment, factory)
                .get(HomeFragmentViewModel::class.java)
            //viewmodel中数据绑定
            setVariable(BR.viewModel, homeViewModel)
            //RecycleView 的adapter相关
            adapter = HomeListsAdapter(R.layout.item_home_lists, lisData)
            recycleView.adapter = adapter
            adapter.bindToRecyclerView(recycleView)
            subscribeUi(homeViewModel, refreshView, statusView)
            addBanner(adapter)
            //进来第一次初始化数据
            homeViewModel.refreshData()
        }
    }

    /**
     * 增加banner
     */
    private fun addBanner(adapter: HomeListsAdapter) {
        val header = layoutInflater.inflate(R.layout.item_home_banner, null)
        bannerView = header.findViewById(R.id.banner)
        bannerView?.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
        //设置图片加载器
        bannerView?.setImageLoader(GlideImageLoader())
        //设置banner动画效果
        bannerView?.setBannerAnimation(Transformer.DepthPage)
        adapter.removeAllHeaderView()
        adapter.addHeaderView(header)
    }


    fun subscribeUi(viewModel: HomeFragmentViewModel, refreshView: SmartRefreshLayout, statusView: MultipleStatusView) {
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

        viewModel.bannerData.observe(viewLifecycleOwner, Observer {
            //处理一下banner数据
            bannerUrl.clear()
            bannerTitile.clear()
            for (banner: Banner in it) {
                bannerUrl.add(banner.imagePath)
                bannerTitile.add(banner.title)
            }
            bannerView?.update(bannerUrl, bannerTitile)

        })
    }

}