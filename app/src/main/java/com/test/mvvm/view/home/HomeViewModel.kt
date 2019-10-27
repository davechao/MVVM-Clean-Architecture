package com.test.mvvm.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.vo.UserItem
import com.test.mvvm.view.adapter.paging.PagingCallback
import com.test.mvvm.view.adapter.paging.UserDataSource
import com.test.mvvm.view.adapter.paging.UserFactory
import com.test.mvvm.view.base.BaseViewModel
import org.koin.core.inject
import timber.log.Timber

class HomeViewModel : BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

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