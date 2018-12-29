package com.renrun.basedevelopjetpack.modules.project.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.base.BaseFragment
import com.renrun.basedevelopjetpack.data.ProjectTreeBean
import com.renrun.basedevelopjetpack.databinding.FragmentProjectBinding
import com.renrun.basedevelopjetpack.modules.project.adapter.ProjectPagerAdapter
import com.renrun.basedevelopjetpack.modules.project.viewmodel.ProjectFragmentViewModel
import com.renrun.basedevelopjetpack.utils.InjectUtils


/**
 * Created by vence on 2018/12/28 14:02
 * 邮箱 ：vence0815@foxmail.com
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    /**
     * ProjectTreeBean
     */
    private var projectTree: MutableList<ProjectTreeBean> = mutableListOf()

    override val layoutId: Int = R.layout.fragment_project

    /**
     * ViewPagerAdapter
     */
    private val viewPagerAdapter: ProjectPagerAdapter by lazy {
        ProjectPagerAdapter(projectTree, childFragmentManager)
    }

    override fun initView() {
        binding.apply {
            val factory = InjectUtils.provideProjectFragmentViewModelFactory(0)
            ViewModelProviders.of(this@ProjectFragment, factory)
                .get(ProjectFragmentViewModel::class.java).apply {
                    getProjectTab()
                    projectTablayout.setupWithViewPager(projectViewpage)
                    tabList.observe(viewLifecycleOwner, Observer {
                        it.let {
                            projectTree.clear()
                            projectTree.addAll(it)
                            projectViewpage.adapter = viewPagerAdapter
                            projectViewpage.offscreenPageLimit = projectTree.size
                        }
                    })
                }
        }
    }
}