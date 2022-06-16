package com.example.localstorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.localstorage.entity.BusinessEntity

@Dao
interface BusinessDao {
    @Query("SELECT * FROM business")
    suspend fun getAll(): List<BusinessEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(businessEntity: BusinessEntity)
}