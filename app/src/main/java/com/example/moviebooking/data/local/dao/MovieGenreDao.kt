package com.example.moviebooking.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviebooking.data.local.entities.MovieGenreEntity

@Dao
interface MovieGenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieGenre: MovieGenreEntity)

    @Query("SELECT * FROM MovieGenres")
    suspend fun getAll(): List<MovieGenreEntity>

    @Query("SELECT * FROM MovieGenres WHERE id = :id")
    suspend fun getById(id: String): MovieGenreEntity?
}