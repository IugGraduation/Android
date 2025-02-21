package com.example.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "home"

fun NavController.navigateToHome(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(ROUTE, builder)
}

fun NavGraphBuilder.homeRoute(navController: NavHostController){
    composable(ROUTE) { HomeScreen(navController) }
}