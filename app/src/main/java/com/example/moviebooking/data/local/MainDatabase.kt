package com.example.moviebooking.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviebooking.data.local.dao.MovieDao
import com.example.moviebooking.data.local.dao.MovieGenreDao
import com.example.moviebooking.data.local.dao.MovieItemDao
import com.example.moviebooking.data.local.dao.UserDao
import com.example.moviebooking.data.local.entities.MovieEntity
import com.example.moviebooking.data.local.entities.MovieGenreEntity
import com.example.moviebooking.data.local.entities.MovieItemEntity
import com.example.moviebooking.data.local.entities.UserInformation

@Database(
    entities = [
        MovieGenreEntity::class,
        MovieEntity::class,
        MovieItemEntity::class,
        UserInformation::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun movieGenreDao(): MovieGenreDao
    abstract fun movieDao(): MovieDao
    abstract fun movieItemDao(): MovieItemDao
    abstract fun userDao(): UserDao
}