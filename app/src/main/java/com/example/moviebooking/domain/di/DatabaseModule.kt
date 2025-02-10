package com.example.moviebooking.domain.di

import androidx.room.Room
import com.example.moviebooking.data.local.MainDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
    fun init() = module {
        single {
            Room.databaseBuilder(
                androidApplication(),
                MainDatabase::class.java,
                "MovieBooking.db"
            ).build()
        }

        factory { get<MainDatabase>().movieDao() }
        factory { get<MainDatabase>().movieItemDao() }
        factory { get<MainDatabase>().userDao() }

    }
}