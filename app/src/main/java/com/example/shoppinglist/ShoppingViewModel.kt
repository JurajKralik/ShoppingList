package com.example.shoppinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.database.ShoppingItem
import com.example.shoppinglist.database.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingViewModel(application: Application): AndroidViewModel(application) {
    private val shoppingRepository = ShoppingRepository(getDatabase(application))

    val shoppingItems = shoppingRepository.shoppingItems
    val item = MutableStateFlow<ShoppingItem?>(null)

    fun addShoppingItem(shoppingItem: ShoppingItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                shoppingRepository.addItem(shoppingItem)
            }
        }
    }

    fun setItemId(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val shoppingItem = shoppingRepository.getItemFromId(id)
                item.value = shoppingItem
            }
        }
    }


}