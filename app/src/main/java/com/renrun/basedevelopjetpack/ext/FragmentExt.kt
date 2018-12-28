package com.renrun.basedevelopjetpack.ext

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.renrun.basedevelopjetpack.app.MyApplication
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * Created by vence on 2018/12/27 11:21
 * 邮箱 ：vence0815@foxmail.com
 */
fun Fragment.showToast(msg: String) {
    Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_LONG).show()
}

fun Fragment.finishFresh(refreshView: SmartRefreshLayout) {
    if (refreshView.isRefreshing) {
        refreshView.finishRefresh()
    }
    if (refreshView.isLoading) {
        refreshView.finishLoadMore()
    }
}
