package com.example.baseproject.domain.remote

import com.example.baseproject.data.BaseDTO

interface RemoteDataSource<D : BaseDTO> {
    suspend fun insert(dto: D)

    suspend fun insert(dtos: List<D>)

    suspend fun getAll(): List<D>

    suspend fun update(dto: D)

    suspend fun delete(dto: D)

    suspend fun findByID(id: String): D?
}
