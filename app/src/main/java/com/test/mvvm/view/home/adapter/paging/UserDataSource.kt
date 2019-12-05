package com.test.mvvm.view.home.adapter.paging

import android.webkit.URLUtil
import androidx.paging.PageKeyedDataSource
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.vo.UserItem
import com.test.mvvm.utility.ResponseUtil
import com.test.mvvm.utility.ResponseUtil.Companion.HEADER_KEY_LINK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
            flow {
                pagingCallback.onLoading()
                val result = apiRepository.fetchUsers(0, 20)
                if (!result.isSuccessful) throw HttpException(result)
                emit(result)
            }
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    pagingCallback.onThrowable(e)
                }.onCompletion {
                    pagingCallback.onLoaded()
                }.collect {
                    if (it.isSuccessful) {
                        it.body()?.run {
                            val nextPageUrl =
                                ResponseUtil.INSTANCE.parseNextPageUrl(
                                    it.headers().get(HEADER_KEY_LINK).toString()
                                )
                            callback.onResult(this, null, nextPageUrl)
                        }
                    }
                }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, UserItem>) {
        val url = params.key
        if (URLUtil.isValidUrl(url)) {
            viewModelScope.launch {
                flow {
                    pagingCallback.onLoading()
                    val result = apiRepository.fetchUsers(url)
                    if (!result.isSuccessful) throw HttpException(result)
                    emit(result)
                }
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        pagingCallback.onThrowable(e)
                    }.onCompletion {
                        pagingCallback.onLoaded()
                    }.collect {
                        if (it.isSuccessful) {
                            it.body()?.run {
                                val nextUrl =
                                    ResponseUtil.INSTANCE.parseNextPageUrl(
                                        it.headers().get(HEADER_KEY_LINK).toString()
                                    )
                                callback.onResult(this, nextUrl)
                            }
                        }
                    }
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, UserItem>) {
        // Nothing to do here
    }
}
