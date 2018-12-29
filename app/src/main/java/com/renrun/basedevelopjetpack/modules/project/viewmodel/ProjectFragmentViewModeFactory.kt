package com.renrun.basedevelopjetpack.modules.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renrun.basedevelopjetpack.modules.project.repository.ProjectFragmentRepository

/**
 * Created by vence on 2018/12/26 11:10
 * 邮箱 ：vence0815@foxmail.com
 * 工厂类，创建一个带有构造参数的ViewModel
 */
class ProjectFragmentViewModeFactory(
    private val repository: ProjectFragmentRepository,
    private val cid:Int
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ProjectFragmentViewModel(
        repository,cid
    ) as T

}