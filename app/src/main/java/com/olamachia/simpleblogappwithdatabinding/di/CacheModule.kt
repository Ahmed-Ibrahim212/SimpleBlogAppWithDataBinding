package com.olamachia.simpleblogappwithdatabinding.di

import androidx.room.Room
import com.olamachia.simpleblogappwithdatabinding.R
import com.olamachia.simpleblogappwithdatabinding.cacheo.AppDatabase
import io.reactivex.schedulers.Schedulers.single
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val cacheModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            androidApplication().baseContext.getString(R.string.app_name)
        ).build()
    }

    single {
        get<AppDatabase>().cacheDao()
    }
}
