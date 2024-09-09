package com.rodriguezmauro.composecomponentscatalog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun MyDialog(show: Boolean, callback: (Boolean) -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = {
                callback(false)
            },
            title = {
                Text(text = "Titulo")
            },
            text = {
                Text(text = "Hola soy una descripcion")
            },
            confirmButton = {
                TextButton(onClick = {
                    callback(true)
                }) {
                    Text(text = "Confirm Button")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    callback(false)
                }) {
                    Text(text = "Dismiss Button")
                }
            }
        )
    }
}