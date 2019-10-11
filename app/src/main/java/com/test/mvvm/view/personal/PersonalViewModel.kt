package com.test.mvvm.view.personal

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.test.mvvm.model.api.ApiRepository
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

    fun getUsers() {
        viewModelScope.launch {
            try {
                val userItemList = withContext(Dispatchers.IO) {
                    apiRepository.fetchUsers()
                }

                Timber.d("Data: ${userItemList.size}")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}