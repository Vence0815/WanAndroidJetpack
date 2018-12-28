package com.renrun.basedevelopjetpack.utils

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import com.renrun.basedevelopjetpack.app.MyApplication

/**
 * Created by vence on 2018/11/7 10:20
 * 邮箱 ：vence0815@foxmail.com
 */
object CommonUtils {
    /**
     * 检查是否有可用网络
     */
    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null
    }


    /**
     * 获取渠道信息
     *
     * @return 对应的渠道的值
     */
    fun getChannel(): String? {
        return getAppMetaData("UMENG_CHANNEL")
    }


    /**
     * 获取application中指定的meta-reFreshData
     *
     * @return 如果没有获取成功(没有对应值 ， 或者异常)，则返回值为空
     */
    private fun getAppMetaData(key: String): String? {
        var resultData: String? = ""
        try {
            val packageManager = MyApplication.getInstance().getPackageManager()
            if (packageManager != null) {
                val applicationInfo = packageManager.getApplicationInfo(
                    MyApplication.getInstance().getPackageName(),
                    PackageManager.GET_META_DATA
                )
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        if (applicationInfo.metaData.getString(key) != null) {
                            resultData = applicationInfo.metaData.getString(key)
                        }
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return resultData
    }

}


