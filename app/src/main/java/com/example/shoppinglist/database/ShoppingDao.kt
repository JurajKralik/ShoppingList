package com.example.shoppinglist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Query("SELECT * FROM shoppingitem")
    fun getAllItems(): Flow<List<ShoppingItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(vararg shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shoppingitem WHERE id=:id")
    fun getItem(id:Int): ShoppingItem

    @Delete
    fun deleteItem(shoppingItem: ShoppingItem)
}