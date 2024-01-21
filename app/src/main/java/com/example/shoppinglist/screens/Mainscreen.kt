package com.example.shoppinglist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shoppinglist.ShoppingViewModel
import com.example.shoppinglist.database.ShoppingItem


@Composable
fun MainScreen(shoppingViewModel: ShoppingViewModel,navHostController: NavHostController) {
    val shoppingItems by shoppingViewModel.shoppingItems.collectAsState(initial = listOf())

    MainScreen(shoppingItems = shoppingItems, onAddItemClick = { shoppingItem -> shoppingViewModel.addShoppingItem(shoppingItem) }, onNavigateToItem = {id -> navHostController.navigate("itemView/$id")} )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(shoppingItems: List<ShoppingItem>, onAddItemClick: (ShoppingItem) -> Unit, onNavigateToItem:(String) -> Unit){

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Shopping list") })
        },
        content =  { innerPadding ->
            Column {
                Input(onAddItemClick,innerPadding)
                ShoppingList(shoppingItems, onNavigateToItem)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(onAddItemClick: (ShoppingItem) -> Unit, padding : PaddingValues) {
    var itemName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        Text(text = "Add item to the shopping list")
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            placeholder = { Text(text = "Product name") }
        )
        TextField(
            value = amount.toString(),
            onValueChange = { amount = if(it.isBlank()) 0 else it.toInt()},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            placeholder = { Text(text = "Product name") }
        )
        Button(onClick = { onAddItemClick(ShoppingItem(itemName,amount)) }) {
            Text(text = "Add product")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList(
    shoppingItems: List<ShoppingItem>,
    onItemClick: (String) -> Unit
){
    LazyColumn{
        items(
            items = shoppingItems
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {onItemClick(it.id.toString())}
            ){
                Text(
                    text = it.name,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}