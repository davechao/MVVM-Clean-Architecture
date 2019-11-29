package com.test.mvvm.view.personal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.Result
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

    val userDetailData = MutableLiveData<Result<UserDetailItem>>()

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            flow {
                emit(Result.loading())
                emit(Result.success(apiRepository.fetchUserDetail(username)))
            }
                .catch { e -> emit(Result.error(e)) }
                .onCompletion { emit(Result.loaded()) }
                .collect { userDetailData.value = it }
        }
    }
}