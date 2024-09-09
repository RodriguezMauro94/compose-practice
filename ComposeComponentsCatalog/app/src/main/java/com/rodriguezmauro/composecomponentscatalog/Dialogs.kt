package com.rodriguezmauro.composecomponentscatalog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyConfirmationDialog(
    show: Boolean,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(12.dp)
            ) {
                MyTitleDialog(text = "Phone ringtone", modifier = Modifier.padding(24.dp))
                HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.LightGray
                )
                var status by remember {
                    mutableStateOf("")
                }
                MyRadioButtonList(name = status) {
                    status = it
                }

                HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.LightGray
                )
                Row(
                    modifier = Modifier.align(Alignment.End).padding(8.dp)
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "CANCEL")
                    }
                    TextButton(onClick = { onConfirm(status) }) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}

@Composable
fun MyCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Set backup account")
                AccountItem("ejemplo1@gmail.com", R.drawable.ic_avatar)
                AccountItem("ejemplo2@gmail.com", R.drawable.ic_avatar)
                AccountItem("ejemplo3@gmail.com", R.drawable.ic_avatar)
                AccountItem("Añadir nueva cuenta", R.drawable.ic_add)
            }
        }
    }
}

@Composable
fun MySimpleCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
            }
        }
    }
}

@Composable
fun MyAlertDialog(show: Boolean, callback: (Boolean) -> Unit) {
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

@Composable
fun MyTitleDialog(text: String, modifier: Modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp, bottom = 8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(
            text = email,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(8.dp)
        )
    }
}