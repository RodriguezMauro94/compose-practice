package com.rodriguezmauro.jetpackcomponentcatalog.recyclerview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Ari", "Pepe", "Mauro")
    LazyColumn {
        item {
            Text(text = "Header")
        }
        items(myList) {
            Text(text = "Hola me llamo $it")
        }
        item {
            Text(text = "Footer")
        }
    }
}