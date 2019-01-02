package com.renrun.basedevelopjetpack.modules.common

import com.just.agentweb.AgentWeb
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.base.BaseFragment
import com.renrun.basedevelopjetpack.databinding.FragmentArticleDetailBinding
import com.renrun.basedevelopjetpack.ext.logE

/**
 * Created by vence on 2018/12/29 15:51
 * 邮箱 ：vence0815@foxmail.com
 */

class ArticleDetailFragment : BaseFragment<FragmentArticleDetailBinding>() {

    private var agentWeb: AgentWeb? = null
    private lateinit var shareTitle: String
    private lateinit var shareUrl: String

    override val layoutId: Int = R.layout.fragment_article_detail

    override fun initView() {
        val link = arguments?.getString("link")
        val title = arguments?.getString("title")
        logE("link---" + link + "----title---" + title)
    }
}