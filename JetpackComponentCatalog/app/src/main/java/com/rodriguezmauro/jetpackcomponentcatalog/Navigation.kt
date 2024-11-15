package com.rodriguezmauro.jetpackcomponentcatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun ScreenOne(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(text = "Pantalla Uno", modifier = Modifier
            .align(Alignment.Center)
            .clickable { navController.navigate(Routes.ScreenTwo.route) })
    }
}

@Composable
fun ScreenTwo(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(text = "Pantalla Dos", modifier = Modifier
            .align(Alignment.Center)
            .clickable { navController.navigate(Routes.ScreenThree.route) })
    }
}

@Composable
fun ScreenThree(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(text = "Pantalla Tres", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                navController.navigate(Routes.ScreenFour.createRoute("pepe"))
            })
    }
}

@Composable
fun ScreenFour(navController: NavHostController, name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Nombre: $name", modifier = Modifier.align(Alignment.Center).clickable { navController.navigate(Routes.ScreenFive.createRoute(18)) })
    }
}

@Composable
fun ScreenFive(navController: NavHostController, age: Int? = 1) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(text = "Tengo: $age años", modifier = Modifier.align(Alignment.Center))
    }
}