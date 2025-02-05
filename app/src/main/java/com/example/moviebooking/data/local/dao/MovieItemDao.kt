package com.example.moviebooking.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviebooking.data.local.entities.MovieItemEntity

interface MovieItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieItem: MovieItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieItems: List<MovieItemEntity>)

    @Query("SELECT * FROM MovieItems")
    suspend fun getAll(): List<MovieItemEntity>

    @Query("SELECT * FROM MovieItems WHERE id = :id")
    suspend fun getById(id: String): MovieItemEntity?
}