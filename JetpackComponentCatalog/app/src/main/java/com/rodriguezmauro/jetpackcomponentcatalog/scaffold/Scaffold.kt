package com.rodriguezmauro.jetpackcomponentcatalog.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScaffoldExample() {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                MyDrawer {
                    coroutineScope.launch { drawerState.apply { close() } }
                }
            }
        }) {
        Scaffold(
            topBar = {
                MyTopAppBar { option ->

                    coroutineScope.launch {
                        if (option.toLowerCase() == "menu") {
                            drawerState.open()
                        } else {
                            snackBarHostState.showSnackbar(
                                message = "Has pulsado $option",
                                duration = SnackbarDuration.Short
                            )
                        }
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
            },
            bottomBar = { MyBottomNavigation() },
            floatingActionButton = { MyFAB() },
            floatingActionButtonPosition = FabPosition.Center
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
            IconButton(onClick = {
                onClick("Menu")
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
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

@Composable
fun MyBottomNavigation() {
    var index by remember {
        mutableIntStateOf(0)
    }
    NavigationBar {
        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Red,
                selectedTextColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                selectedIconColor = Color.White,
                unselectedIconColor = Color.Gray
            ),
            label = {
                Text(text = "Home")
            }
        )

        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favoritos"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Red,
                selectedTextColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                selectedIconColor = Color.White,
                unselectedIconColor = Color.Gray
            ),
            label = {
                Text(text = "Favs")
            }
        )

        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Red,
                selectedTextColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                selectedIconColor = Color.White,
                unselectedIconColor = Color.Gray
            ),
            label = {
                Text(text = "Perfil")
            }
        )
    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(onClick = { }, containerColor = Color.Yellow, contentColor = Color.Black) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(
        Modifier.padding(8.dp)
    ) {
        Text(text = "Primera opción", modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCloseDrawer() }
        )

        Text(text = "Segunda opción", modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCloseDrawer() }
        )

        Text(text = "Tercera opción", modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCloseDrawer() }
        )
    }
}