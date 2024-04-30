package com.example.shoppinglist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppinglist.ShoppingViewModel
import com.example.shoppinglist.screens.ItemScreen
import com.example.shoppinglist.screens.MainScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    shoppingViewModel: ShoppingViewModel
){
    NavHost(navController = navHostController, startDestination= "mainScreen"){
        composable("mainScreen"){
            MainScreen(shoppingViewModel, navHostController)
        }
        composable("itemView/{itemId}"){navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getString("itemId")
            itemId?.let { 
                ItemScreen(shoppingViewModel = shoppingViewModel, itemId = itemId, navController = navHostController)
            }
        }
    }
}