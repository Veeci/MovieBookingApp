package com.example.baseproject.data

open class BaseModel(
    open var id: String? = null,
)

fun BaseModel.mapToEntity(): BaseEntity {
    return BaseEntity(id = this.id)
}
