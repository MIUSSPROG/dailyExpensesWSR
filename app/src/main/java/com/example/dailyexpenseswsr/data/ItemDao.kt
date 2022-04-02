package com.example.dailyexpenseswsr.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item): Long

    @Query("SELECT * FROM Item WHERE date = :pickDate")
    fun getAllItems(pickDate: Long): Flow<List<Item>>

    @Query("SELECT * FROM Item")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT date, SUM(price) as 'sumPrice' FROM Item WHERE date BETWEEN :fromDate AND :toDate GROUP BY date")
    fun getAllItemsInRange(fromDate: Long, toDate: Long): Flow<List<GraphicsData>>
}