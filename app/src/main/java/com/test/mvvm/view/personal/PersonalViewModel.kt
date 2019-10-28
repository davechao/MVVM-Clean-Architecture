package com.test.mvvm.view.personal

import androidx.lifecycle.asLiveData
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.Result
import com.test.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import org.koin.core.inject

class PersonalViewModel : BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

//    fun getUserDetail(username: String) =
//        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
//            try {
//                emit(Result.loading())
//                emit(Result.success(apiRepository.fetchUserDetail(username)))
//            } catch (e: Exception) {
//                emit(Result.error(e))
//            } finally {
//                emit(Result.loaded())
//            }
//        }

    fun getUserDetail(username: String) =
        flow {
            emit(Result.loading())
            emit(Result.success(apiRepository.fetchUserDetail(username)))
        }
            .catch { e -> emit(Result.error(e)) }
            .onCompletion { emit(Result.loaded()) }
            .asLiveData()

}