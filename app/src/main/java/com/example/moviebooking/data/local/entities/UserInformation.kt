package com.example.moviebooking.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserInformation(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String? = "",
    @ColumnInfo(name = "password")
    val password: String? = ""
)