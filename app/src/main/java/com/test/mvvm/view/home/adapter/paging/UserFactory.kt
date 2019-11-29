package com.test.mvvm.view.home.adapter.paging

import androidx.paging.DataSource
import com.test.mvvm.model.api.vo.UserItem

class UserFactory constructor(
    private val userDataSource: UserDataSource
) : DataSource.Factory<String, UserItem>() {
    override fun create(): DataSource<String, UserItem> {
        return userDataSource
    }
}

