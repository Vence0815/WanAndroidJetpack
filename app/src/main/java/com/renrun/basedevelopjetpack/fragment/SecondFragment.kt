package com.renrun.basedevelopjetpack.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.base.BaseFragment
import com.renrun.basedevelopjetpack.databinding.FragmentSecondBinding
import com.renrun.basedevelopjetpack.ext.logE
import com.renrun.basedevelopjetpack.ext.logI

/**
 * Created by vence on 2018/12/20 16:51
 * 邮箱 ：vence0815@foxmail.com
 */
class SecondFragment : BaseFragment<FragmentSecondBinding>() {

    override val layoutId: Int = R.layout.fragment_second

    override fun initView() {
        logI("SecondFragment------" + "initView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logI("SecondFragment------" + "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI("SecondFragment------" + "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logI("SecondFragment------" + "onActivityCreated")
    }


    override fun onResume() {
        super.onResume()
        logI("SecondFragment------" + "onResume")
    }

    override fun onPause() {
        super.onPause()
        logI("SecondFragment------" + "onPause")
    }


    override fun onStart() {
        super.onStart()
        logI("SecondFragment------" + "onStart")
    }

    override fun onStop() {
        super.onStop()
        logI("SecondFragment------" + "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logI("SecondFragment------" + "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logI("SecondFragment------" + "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        logI("SecondFragment------" + "onDetach")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        logE("SecondFragment-----$isVisibleToUser")
    }
}