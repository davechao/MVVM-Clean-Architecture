package com.test.mvvm.view.personal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.bean.UserDetailItem
import com.test.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject

class PersonalViewModel : BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

    val userDetailData = MutableLiveData<UserDetailItem>()

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true

                val userDetailItem = withContext(Dispatchers.IO) {
                    apiRepository.fetchUserDetail(username)
                }

                userDetailData.value = userDetailItem

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }
}