package com.renrun.basedevelopjetpack.utils

import com.renrun.basedevelopjetpack.modules.home.repository.HomeFragmentRepository
import com.renrun.basedevelopjetpack.modules.home.viewmodel.HomeFragmentViewModeFactory
import com.renrun.basedevelopjetpack.modules.project.repository.ProjectFragmentRepository
import com.renrun.basedevelopjetpack.modules.project.viewmodel.ProjectFragmentViewModeFactory

/**
 * Created by vence on 2018/12/26 11:28
 * 邮箱 ：vence0815@foxmail.com
 * 注入类，通过这个类创建viewmodel的factory，在这里把对应的repository对象初始化传入viewmodel达到解耦的目的，就不用在viewmodel里面
 * 创建repository相关对象了
 */
object InjectUtils {

    /**
     * 首页ViewModel
     */
    fun provideHomeFragmentViewModelFactory(): HomeFragmentViewModeFactory =
        HomeFragmentViewModeFactory(HomeFragmentRepository.getInstance())

    /**
     * 项目列表viewModel
     */
    fun provideProjectFragmentViewModelFactory(cid:Int): ProjectFragmentViewModeFactory =
        ProjectFragmentViewModeFactory(ProjectFragmentRepository.getInstance(),cid)
}