package com.test.mvvm.view.personal

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.bean.UserDetailItem
import com.test.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class PersonalViewModel @Inject constructor(
    app: Application,
    private val apiRepository: ApiRepository
) : BaseViewModel(app) {

    private val applicationContext = app.applicationContext

    val userDetailData = MutableLiveData<UserDetailItem>()

    fun getUserDetail(login: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val userDetailItem = withContext(Dispatchers.IO) {
                    apiRepository.fetchUserDetail(login)
                }

                isLoading.value = false
                userDetailData.value = userDetailItem

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}