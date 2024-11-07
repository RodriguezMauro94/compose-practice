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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MyTopAppBar {
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(
                        message = "Has pulsado $it",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState, snackbar = {
                Snackbar(
                    snackbarData = it,
                    containerColor = Color.LightGray,
                    contentColor = Color.Blue
                )
            })
        }
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
fun MyTopAppBar(onClick: (String) -> Unit) {
    TopAppBar(
        title = { Text(text = "Mi primera toolbar") },
        colors = TopAppBarColors(
            containerColor = Color.Red,
            scrolledContainerColor = Color.Red,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { onClick("Back") }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { onClick("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = { onClick("Warning") }) {
                Icon(imageVector = Icons.Filled.Warning, contentDescription = "Warning")
            }
        })
}