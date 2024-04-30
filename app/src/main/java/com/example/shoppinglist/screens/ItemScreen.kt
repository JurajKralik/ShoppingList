package com.example.shoppinglist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shoppinglist.ShoppingViewModel
import com.example.shoppinglist.database.ShoppingItem

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
            Column(modifier = Modifier
                .padding(innerPadding)
            ){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)) {
                    Text(text = item?.name.toString(),
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center)
                }
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)) {
                    Text(text = item?.amount.toString() + " " + item?.uom,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center)
                }
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)) {
                    Text(
                        text = "Note: " + item?.note,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                    onClick = { onClickDelete(shoppingViewModel, item, navController) })
                {
                    Text(text = "Bought", color= Color.White)
                }
            }
        }
    )
}

fun onClickDelete(
    shoppingViewModel: ShoppingViewModel,
    item: ShoppingItem?,
    navController: NavController
){
    item?.let { shoppingViewModel.deleteUser(it) }
    navController.navigateUp()
}

