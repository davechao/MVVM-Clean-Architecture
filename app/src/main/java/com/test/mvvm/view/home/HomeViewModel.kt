package com.test.mvvm.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.vo.UserItem
import com.test.mvvm.view.home.adapter.paging.PagingCallback
import com.test.mvvm.view.home.adapter.paging.UserDataSource
import com.test.mvvm.view.home.adapter.paging.UserFactory
import com.test.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.inject
import timber.log.Timber

class HomeViewModel : BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

    private val _userListData = MutableLiveData<PagedList<UserItem>>()
    val userListData: LiveData<PagedList<UserItem>> = _userListData

    fun getUsers() {
        viewModelScope.launch {
            getPagingItems().asFlow().collect {
                _userListData.value = it
            }
        }
    }

    private fun getPagingItems(): LiveData<PagedList<UserItem>> {
        val userDataSource = UserDataSource(viewModelScope, apiRepository, callback)
        val userFactory = UserFactory(userDataSource)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .build()
        return LivePagedListBuilder(userFactory, config).build()
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
