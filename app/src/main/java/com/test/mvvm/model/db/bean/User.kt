package com.test.mvvm.model.db.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey var userId: String,
    @ColumnInfo(name = "user_name") var userName: String? = null
)