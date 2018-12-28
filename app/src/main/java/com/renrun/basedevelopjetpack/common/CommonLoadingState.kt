package com.renrun.basedevelopjetpack.common

/**
 * Created by vence on 2018/12/27 14:41
 * 邮箱 ：vence0815@foxmail.com
 */
enum class LoadingState {
    ERROR, EMPTY, LOADING, NETERROR, SUCCESS
}

enum class FreshStatus {
    REFRESH, LOADMORE
}

class CommonLoadingState(val loadingState: LoadingState, val freshStatus: FreshStatus, val errorMsg: String) {

}