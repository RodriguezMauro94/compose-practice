package com.rodriguezmauro.composecomponentscatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodriguezmauro.composecomponentscatalog.ui.theme.ComposeComponentsCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeComponentsCatalogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        var myText by rememberSaveable {
                            mutableStateOf("")
                        }
                        MyTextField("Ejemplo de state hosting") {
                            myText = it
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeComponentsCatalogTheme {
        MyTextFieldOutlined()
    }
}

@Composable
fun MyTextFieldOutlined() {
    Column(Modifier.fillMaxSize()) {
        var myText by rememberSaveable {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = myText,
            onValueChange = {
                myText = it
            },
            label = {
                Text(text = "Introduce tu nombre")
            },
            modifier = Modifier.padding(all = 24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Red,
                focusedBorderColor = Color.Red,
                unfocusedTextColor = Color.Blue,
                unfocusedBorderColor = Color.Blue
            ),
            singleLine = true
        )
    }
}

@Composable
fun MyTextFieldAdvanced() {
    Column(Modifier.fillMaxSize()) {
        var myText by rememberSaveable {
            mutableStateOf("")
        }

        TextField(value = myText, onValueChange = {
            myText = if (it.contains("a")) it.replace("a", "") else it
        }, label = {
            Text(text = "Introduce tu nombre")
        })
    }
}

@Composable
fun MyTextField(name: String, onValueChanged:(String) -> Unit ) {
    Column(Modifier.fillMaxSize()) {
        TextField(value = name, onValueChange = { onValueChanged(it) })
    }
}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Medium)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Esto es un ejemplo", fontFamily = FontFamily.Cursive)
        Text(text = "Esto es un ejemplo", textDecoration = TextDecoration.LineThrough)
        Text(text = "Esto es un ejemplo", textDecoration = TextDecoration.Underline)
        Text(text = "Esto es un ejemplo", textDecoration = TextDecoration.combine(
            listOf(
                TextDecoration.LineThrough,
                TextDecoration.Underline
            )
        ))
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}