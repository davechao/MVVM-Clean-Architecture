package com.test.mvvm.view.home.adapter.paging

interface PagingCallback {
    fun onLoading()
    fun onLoaded()
    fun onTotalCount(count: Int)
    fun onThrowable(throwable: Throwable)

}