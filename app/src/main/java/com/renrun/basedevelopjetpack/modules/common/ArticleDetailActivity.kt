package com.renrun.basedevelopjetpack.modules.common

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.NestedScrollAgentWebView
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.ext.logE
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * Created by vence on 2018/12/29 15:51
 * 邮箱：vence0815@foxmail.com
 */

class ArticleDetailActivity : AppCompatActivity(){

    private lateinit var mAgentWeb: AgentWeb
    private lateinit var shareTitle: String
    private lateinit var webUrl: String
    private val mWebView: NestedScrollAgentWebView by lazy {
        NestedScrollAgentWebView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        webUrl = intent.getStringExtra("link")
        shareTitle = intent.getStringExtra("title")
        logE("link---" + webUrl + "----title---" + shareTitle)
        tv_title.text = shareTitle
        initToolBar()
        initWeb()
    }

    private fun initToolBar() {
        toolbar.apply {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        tv_title.apply {
            visibility = View.VISIBLE
            postDelayed({
                tv_title.isSelected = true
            }, 1000)
        }

        toolbar.setNavigationOnClickListener {
            if (mAgentWeb.webCreator.webView.canGoBack()) {
                mAgentWeb.back()
            } else {
                finish()
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWeb() {
        val layoutParams = CoordinatorLayout.LayoutParams(-1, -1)
        layoutParams.behavior = AppBarLayout.ScrollingViewBehavior()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(container, layoutParams)
            .useDefaultIndicator()
            .setWebView(mWebView)
            .createAgentWeb()
            .ready()
            .go(webUrl)
        val mWebView = mAgentWeb.getWebCreator()?.getWebView()
        val mSettings = mWebView?.getSettings()
        mSettings?.setJavaScriptEnabled(true)
        mSettings?.setSupportZoom(true)
        mSettings?.setBuiltInZoomControls(true)
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings?.setUseWideViewPort(true)
        //缩放至屏幕的大小
        mSettings?.setLoadWithOverviewMode(true)
    }
}