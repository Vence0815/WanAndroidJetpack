package com.renrun.basedevelopjetpack.ext

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.renrun.basedevelopjetpack.app.MyApplication
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import es.dmoral.toasty.Toasty

/**
 * Created by vence on 2018/12/27 11:21
 * 邮箱 ：vence0815@foxmail.com
 */

fun Fragment.finishFresh(refreshView: SmartRefreshLayout) {
    if (refreshView.isRefreshing) {
        refreshView.finishRefresh()
    }
    if (refreshView.isLoading) {
        refreshView.finishLoadMore()
    }
}


fun Fragment.toastErrorWithicon(msg: String) {
    Toasty.error(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT, true).show()
}

fun Fragment.toastSuccessWithicon(msg: String) {
    Toasty.success(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT, true).show()
}

fun Fragment.toastInfoWithicon(msg: String) {
    Toasty.info(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT, true).show()
}

fun Fragment. toastWarningWithicon(msg: String) {
    Toasty.warning(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT, true).show()
}

fun Fragment.toastNormal(msg: String) {
    Toasty.normal(MyApplication.getInstance(), msg).show()
}
