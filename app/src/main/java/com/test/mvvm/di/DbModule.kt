package com.test.mvvm.di

import androidx.room.Room
import com.test.mvvm.App
import com.test.mvvm.Constant
import com.test.mvvm.model.db.AppDatabase
import com.test.mvvm.model.db.dao.UserDao
import org.koin.dsl.module

val dbModule = module {
    single { provideAppDatabase() }
    single { provideUserDao(get()) }
}

fun provideAppDatabase(): AppDatabase {
    return Room.databaseBuilder(
        App.applicationContext(),
        AppDatabase::class.java,
        Constant.DB_NAME
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}

fun provideUserDao(appDatabase: AppDatabase): UserDao {
    return appDatabase.userDao()
}