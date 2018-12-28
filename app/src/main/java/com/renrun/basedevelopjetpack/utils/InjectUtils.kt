package com.renrun.basedevelopjetpack.utils

import com.renrun.basedevelopjetpack.modules.home.repository.FirstFragmentRepository
import com.renrun.basedevelopjetpack.modules.home.repository.HomeFragmentRepository
import com.renrun.basedevelopjetpack.modules.home.viewmodel.FirstFragmentViewModeFactory
import com.renrun.basedevelopjetpack.modules.home.viewmodel.HomeFragmentViewModeFactory

/**
 * Created by vence on 2018/12/26 11:28
 * 邮箱 ：vence0815@foxmail.com
 * 注入类，通过这个类创建viewmodel的factory，在这里把对应的repository对象初始化传入viewmodel达到解耦的目的，就不用在viewmodel里面
 * 创建repository相关对象了
 */
object InjectUtils {

    /**
     * 提供工厂类，传入需要的Repository对象
     */
    fun provideFirstFragmentViewModelFactory(): FirstFragmentViewModeFactory =
        FirstFragmentViewModeFactory(FirstFragmentRepository.getInstance())


    /**
     * 首页ViewModel
     */
    fun provideHomeFragmentViewModelFactory(): HomeFragmentViewModeFactory =
        HomeFragmentViewModeFactory(HomeFragmentRepository.getInstance())
}