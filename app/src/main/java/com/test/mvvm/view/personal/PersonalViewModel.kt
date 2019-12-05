package com.test.mvvm.view.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.ApiResult
import com.test.mvvm.model.api.vo.UserDetailItem
import com.test.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.inject
import retrofit2.HttpException

class PersonalViewModel : BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

    private val _userDetailData = MutableLiveData<ApiResult<UserDetailItem>>()
    val userDetailData: LiveData<ApiResult<UserDetailItem>> = _userDetailData

    private val _noContentData = MutableLiveData<ApiResult<Void>>()
    val noContentData: LiveData<ApiResult<Void>> = _noContentData

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            flow {
                val result = apiRepository.fetchUserDetail(username)
                if (!result.isSuccessful) throw HttpException(result)
                emit(ApiResult.success(result.body()))
            }
                .flowOn(Dispatchers.IO)
                .onStart { emit(ApiResult.loading()) }
                .catch { e -> emit(ApiResult.error(e)) }
                .onCompletion { emit(ApiResult.loaded()) }
                .collect { _userDetailData.value = it }
        }
    }

    fun testNoContentApi() {
        viewModelScope.launch {
            flow {
                emit(ApiResult.loading())
                val result = apiRepository.testNoContentApi()
                if (!result.isSuccessful) throw HttpException(result)
                emit(ApiResult.success(result.body()))
            }
                .flowOn(Dispatchers.IO)
                .onStart { emit(ApiResult.loading()) }
                .catch { e -> emit(ApiResult.error(e)) }
                .onCompletion { emit(ApiResult.loaded()) }
                .collect { _noContentData.value = it }
        }
    }
}
