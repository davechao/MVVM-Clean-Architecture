package com.test.mvvm.view.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.ApiResult
import com.test.mvvm.model.api.vo.UserDetailItem
import com.test.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import org.koin.core.inject

class PersonalViewModel : BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

    private val _userDetailData = MutableLiveData<ApiResult<UserDetailItem>>()
    val userDetailData: LiveData<ApiResult<UserDetailItem>> = _userDetailData

    private val _noContentData = MutableLiveData<ApiResult<Void?>>()
    val noContentData: LiveData<ApiResult<Void?>> = _noContentData

    fun testNoContentApi() {
        viewModelScope.launch {
            flow {
                emit(ApiResult.loading())
                emit(ApiResult.success(apiRepository.testNoContentApi().body()))
            }
                .catch { e -> emit(ApiResult.error(e)) }
                .onCompletion { emit(ApiResult.loaded()) }
                .collect {
                    _noContentData.value = it
                }
        }
    }

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            flow {
                emit(ApiResult.loading())
                emit(ApiResult.success(apiRepository.fetchUserDetail(username).body()!!))
            }
                .catch { e -> emit(ApiResult.error(e)) }
                .onCompletion { emit(ApiResult.loaded()) }
                .collect { _userDetailData.value = it }
        }
    }
}