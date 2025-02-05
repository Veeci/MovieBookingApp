package com.example.baseproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    Entity represents the database-level data.
 */
@Entity
open class BaseEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    open var id: String,
)