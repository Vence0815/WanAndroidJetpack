package com.renrun.basedevelopjetpack.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.databinding.FragmentMainBinding
import com.renrun.basedevelopjetpack.ext.logSimpleI
import com.renrun.basedevelopjetpack.ui.home.Fragment.FirstFragment
import com.renrun.basedevelopjetpack.fragment.SecondFragment
import com.renrun.basedevelopjetpack.fragment.ThirdFragment
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * Created by vence on 2018/12/18 13:57
 * 邮箱 ：vence0815@foxmail.com
 * MainActivity的填充物，为了Fragment不被重新创建使用的折中的办法
 */
class MainFragment : Fragment() {
    lateinit var fragments: List<Fragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logSimpleI("MainFragment------" + "onCreateView")
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater, R.layout.fragment_main, container, false
        )
        fragments = listOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment(),
            ThirdFragment(),
            ThirdFragment()
        )
        binding.mainViewPage.adapter =
                ViewPageAdapter(childFragmentManager, fragments)
        binding.mainViewPage.offscreenPageLimit = fragments.size
        initPage(binding)
        return binding.root
    }

    private fun initPage(binding: FragmentMainBinding) {
        binding.mainViewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(index: Int) {
                for (position in 0..index) {
                    binding.bottomnavigation.menu.getItem(position).isChecked = index == position
                }
            }
        })

        binding.bottomnavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> mainViewPage.setCurrentItem(0,false)
                R.id.action_knowledge_system -> mainViewPage.setCurrentItem(1,false)
                R.id.action_wechat -> mainViewPage.setCurrentItem(2,false)
                R.id.action_navigation -> mainViewPage.setCurrentItem(3,false)
                R.id.action_project -> mainViewPage.setCurrentItem(4,false)
            }
            false
        }
    }

    class ViewPageAdapter(fm: FragmentManager, var fragments: List<Fragment>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment = fragments.get(position)

        override fun getCount(): Int = fragments.size
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logSimpleI("MainFragment------" + "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logSimpleI("MainFragment------" + "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logSimpleI("MainFragment------" + "onActivityCreated")
    }


    override fun onResume() {
        super.onResume()
        logSimpleI("MainFragment------" + "onResume")
    }

    override fun onPause() {
        super.onPause()
        logSimpleI("MainFragment------" + "onPause")
    }


    override fun onStart() {
        super.onStart()
        logSimpleI("MainFragment------" + "onStart")
    }

    override fun onStop() {
        super.onStop()
        logSimpleI("MainFragment------" + "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logSimpleI("MainFragment------" + "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logSimpleI("MainFragment------" + "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        logSimpleI("MainFragment------" + "onDetach")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        logSimpleI("MainFragment-----$isVisibleToUser")
    }
}