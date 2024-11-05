package com.rodriguezmauro.jetpackcomponentcatalog.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    Scaffold(
        topBar = { MyTopAppBar() }
    ) { padding ->
        Box(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Color.Red)
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(title = { Text(text = "Mi primera toolbar") }, colors = TopAppBarColors(containerColor = Color.Red, scrolledContainerColor = Color.Red, navigationIconContentColor = Color.White, titleContentColor = Color.White, actionIconContentColor = Color.White), navigationIcon = {
        IconButton(onClick = {  }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
    }, actions = {
        IconButton(onClick = {  }) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        }
        IconButton(onClick = {  }) {
            Icon(imageVector = Icons.Filled.Warning, contentDescription = "Warning")
        }
    })
}