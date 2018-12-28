package com.renrun.basedevelopjetpack.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.base.BaseFragment
import com.renrun.basedevelopjetpack.databinding.FragmentThirdBinding
import com.renrun.basedevelopjetpack.ext.logE
import com.renrun.basedevelopjetpack.ext.logI

/**
 * Created by vence on 2018/12/20 16:51
 * 邮箱 ：vence0815@foxmail.com
 */
class ThirdFragment : BaseFragment<FragmentThirdBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_third

    override fun initView() {
        logI("ThirdFragment------" + "initView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logI("ThirdFragment------" + "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI("ThirdFragment------" + "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logI("ThirdFragment------" + "onActivityCreated")
    }


    override fun onResume() {
        super.onResume()
        logI("ThirdFragment------" + "onResume")
    }

    override fun onPause() {
        super.onPause()
        logI("ThirdFragment------" + "onPause")
    }


    override fun onStart() {
        super.onStart()
        logI("ThirdFragment------" + "onStart")
    }

    override fun onStop() {
        super.onStop()
        logI("ThirdFragment------" + "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logI("ThirdFragment------" + "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logI("ThirdFragment------" + "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        logI("ThirdFragment------" + "onDetach")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        logE("ThirdFragment-----$isVisibleToUser")
    }

}