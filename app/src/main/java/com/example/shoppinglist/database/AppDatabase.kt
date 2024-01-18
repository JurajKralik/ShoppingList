package com.example.shoppinglist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShoppingItem::class], version = 0)
abstract class AppDatabase: RoomDatabase() {
}

private lateinit var INSTANCE: AppDatabase
