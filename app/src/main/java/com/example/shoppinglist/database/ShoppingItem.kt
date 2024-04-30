package com.example.shoppinglist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingItem(
    val name:String,
    val amount: Int,
    val uom: String = "Pieces",
    val note: String = "",
    @PrimaryKey(autoGenerate = true)val id: Int = 0
)
