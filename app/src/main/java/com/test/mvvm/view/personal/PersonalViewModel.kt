package com.test.mvvm.view.personal

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.Result
import com.test.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.inject

class PersonalViewModel : BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

    fun getUserDetail(username: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                emit(Result.loading())
                emit(Result.success(apiRepository.fetchUserDetail(username)))
            } catch (e: Exception) {
                emit(Result.error(e))
            } finally {
                emit(Result.loaded())
            }
        }

}