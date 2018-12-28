package com.renrun.basedevelopjetpack.modules.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renrun.basedevelopjetpack.modules.home.repository.HomeFragmentRepository

/**
 * Created by vence on 2018/12/26 11:10
 * 邮箱 ：vence0815@foxmail.com
 * 工厂类，创建一个带有构造参数的ViewModel
 */
class HomeFragmentViewModeFactory(
    private val repository: HomeFragmentRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeFragmentViewModel(
        repository
    ) as T

}