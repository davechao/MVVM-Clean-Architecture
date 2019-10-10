package com.test.mvvm.di.module

import androidx.room.Room
import com.test.mvvm.App
import com.test.mvvm.Constant
import com.test.mvvm.model.db.AppDatabase
import com.test.mvvm.model.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
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

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

}
