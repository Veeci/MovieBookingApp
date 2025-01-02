package com.example.baseproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class BaseEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String? = null,
)

fun BaseEntity.mapToModel(): BaseModel {
    return BaseModel(id = this.id)
}

fun List<BaseEntity>.mapToModels() = map { it.mapToModel() }
