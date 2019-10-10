package com.test.mvvm.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.mvvm.model.db.bean.User
import com.test.mvvm.model.db.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}