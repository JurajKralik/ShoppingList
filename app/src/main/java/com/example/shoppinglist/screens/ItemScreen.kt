package com.example.shoppinglist.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.shoppinglist.ShoppingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(
    shoppingViewModel: ShoppingViewModel,
    itemId: String,
    navController: NavController
) {
    shoppingViewModel.setItemId(itemId.toInt())
    val item by shoppingViewModel.item.collectAsState(initial = null)
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Item view") },
                navigationIcon =  if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }
            )
        },
        content = {innerPadding ->
            Row(modifier = Modifier.padding(innerPadding)) {
                Text(text = item?.name + ": "+ item?.amount)
            }
        }
    )
}