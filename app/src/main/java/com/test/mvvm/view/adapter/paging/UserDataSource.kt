package com.test.mvvm.view.adapter.paging

import android.webkit.URLUtil
import androidx.paging.PageKeyedDataSource
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.bean.UserItem
import com.test.mvvm.utility.ResponseUtil
import com.test.mvvm.utility.ResponseUtil.Companion.HEADER_KEY_LINK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDataSource constructor(
    private val viewModelScope: CoroutineScope,
    private val apiRepository: ApiRepository,
    private val pagingCallback: PagingCallback
) : PageKeyedDataSource<String, UserItem>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, UserItem>
    ) {
        viewModelScope.launch {
            try {
                pagingCallback.onLoading()

                val response = withContext(Dispatchers.IO) {
                    apiRepository.fetchUsers(0, 20)
                }

                if (response.isSuccessful) {
                    response.body()?.run {
                        val nextPageUrl =
                            ResponseUtil.INSTANCE.parseNextPageUrl(
                                response.headers().get(HEADER_KEY_LINK).toString()
                            )
                        callback.onResult(this, null, nextPageUrl)
                    }
                }
            } catch (e: Throwable) {
                pagingCallback.onThrowable(e)
            } finally {
                pagingCallback.onLoaded()
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, UserItem>) {
        val url = params.key
        if (URLUtil.isValidUrl(url)) {
            viewModelScope.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        apiRepository.fetchUsers(url)
                    }

                    if (response.isSuccessful) {
                        response.body()?.run {
                            val nextUrl =
                                ResponseUtil.INSTANCE.parseNextPageUrl(
                                    response.headers().get(HEADER_KEY_LINK).toString()
                                )
                            callback.onResult(this, nextUrl)
                        }
                    }
                } catch (e: Throwable) {
                    pagingCallback.onThrowable(e)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, UserItem>) {
        // Nothing to do here
    }
}