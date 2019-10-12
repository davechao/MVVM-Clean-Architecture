package com.test.mvvm.view.adapter.paging

interface PagingCallback {
    fun onLoading()
    fun onLoaded()
    fun onTotalCount(count: Int)
    fun onThrowable(throwable: Throwable)

}