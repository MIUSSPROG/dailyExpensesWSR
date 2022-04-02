package com.example.dailyexpenseswsr.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [Item::class]
)
abstract class MainDatabase(): RoomDatabase() {
    abstract val mainDao: ItemDao
}