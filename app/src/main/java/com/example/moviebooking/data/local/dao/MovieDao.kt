package com.example.moviebooking.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviebooking.data.local.entities.MovieEntity

interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM Movies")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM Movies WHERE id = :id")
    suspend fun getById(id: String): MovieEntity?

}