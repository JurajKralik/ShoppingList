package com.example.shoppinglist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.database.ShoppingItem
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<ShoppingViewModel>()
            val shoppingItems by viewModel.shoppingItems.collectAsState(initial = listOf())
            ShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(shoppingItems = shoppingItems, onAddItemClick = {shoppingItem -> viewModel.addShoppingItem(shoppingItem) })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(shoppingItems: List<ShoppingItem>,onAddItemClick: (ShoppingItem) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Shopping list") })
        },
        content =  { innerPadding ->
            Column {
                Input(onAddItemClick,innerPadding)
                ShoppingList(shoppingItems)
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
fun ShoppingList(shoppingItems: List<ShoppingItem>){
    LazyColumn{
        items(
            items = shoppingItems
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {}
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        MainScreen(listOf(), {})
    }
}