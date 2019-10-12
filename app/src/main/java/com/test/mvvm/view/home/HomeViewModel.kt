package com.test.mvvm.view.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.bean.UserItem
import com.test.mvvm.view.adapter.paging.PagingCallback
import com.test.mvvm.view.adapter.paging.UserDataSource
import com.test.mvvm.view.adapter.paging.UserFactory
import com.test.mvvm.view.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    app: Application,
    private val apiRepository: ApiRepository
) : BaseViewModel(app) {

    private val applicationContext = app.applicationContext

    fun getUsers(): LiveData<PagedList<UserItem>> {
        val userDataSource = UserDataSource(viewModelScope, apiRepository, callback)
        val userFactory = UserFactory(userDataSource)
        val config = PagedList.Config.Builder()
            .setPrefetchDistance(3)
            .build()
        return LivePagedListBuilder(userFactory, config)
            .build()
    }

    private val callback = object : PagingCallback {
        override fun onLoading() {
            isLoading.value = true
        }

        override fun onLoaded() {
            isLoading.value = false
        }

        override fun onTotalCount(count: Int) {
            // Nothing to do here
        }

        override fun onThrowable(throwable: Throwable) {
            Timber.e("Error: $throwable")
        }
    }

}