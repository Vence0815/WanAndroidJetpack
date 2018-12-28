package com.renrun.basedevelopjetpack.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renrun.basedevelopjetpack.ui.home.repository.FirstFragmentRepository

/**
 * Created by vence on 2018/12/26 11:10
 * 邮箱 ：vence0815@foxmail.com
 * 工厂类，创建一个带有构造参数的ViewModel
 */
class FirstFragmentViewModeFactory(
    private val repository: FirstFragmentRepository
) :ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FirstFragmentViewModel(
        repository
    ) as T

}