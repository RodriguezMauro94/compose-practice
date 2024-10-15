package com.rodriguezmauro.jetpackcomponentcatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodriguezmauro.jetpackcomponentcatalog.recyclerview.SimpleRecyclerView
import com.rodriguezmauro.jetpackcomponentcatalog.recyclerview.SuperHeroGridView
import com.rodriguezmauro.jetpackcomponentcatalog.recyclerview.SuperHeroView
import com.rodriguezmauro.jetpackcomponentcatalog.ui.theme.JetpackComponentCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComponentCatalogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SuperHeroGridView()
                }
            }
        }
    }
}


@Composable
fun MyStateExample(modifier: Modifier? = null) {
    var counter by rememberSaveable {
        mutableIntStateOf(0)
    }
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            counter += 1
        }) {
            Text(text = "Pulsar")
        }

        Text(text = "He sido pulsado $counter veces")
    }
}

@Composable
fun MyComplexLayout(modifier: Modifier? = null) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Cyan),
            contentAlignment = Alignment.Center){
            Text(text = "Ejemplo 1")
        }
        MySpacer(30)
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.Red),
                contentAlignment = Alignment.Center){
                Text(text = "Ejemplo 2")
            }
            Spacer(modifier = Modifier.width(30.dp))
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.Green),
                contentAlignment = Alignment.Center) {
                Text(text = "Ejemplo 3")
            }
        }
        MySpacer(80)
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Magenta),
            contentAlignment = Alignment.BottomCenter){
            Text(text = "Ejemplo 4")
        }
    }
}

@Composable
fun MySpacer(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun MyRow(modifier: Modifier? = null) {
//    Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
//        Text(text = "Ejemplo 1")
//        Text(text = "Ejemplo 2")
//        Text(text = "Ejemplo 3")
//        Text(text = "Ejemplo 4")
//    }
    Row(
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Ejemplo 1", modifier = Modifier.width(100.dp))
        Text(text = "Ejemplo 2", modifier = Modifier.width(100.dp))
        Text(text = "Ejemplo 3", modifier = Modifier.width(100.dp))
        Text(text = "Ejemplo 4", modifier = Modifier.width(100.dp))
        Text(text = "Ejemplo 5", modifier = Modifier.width(100.dp))
        Text(text = "Ejemplo 6", modifier = Modifier.width(100.dp))
    }
}

@Composable
fun MyColumn(modifier: Modifier? = null) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.SpaceEvenly ) {
        Text(text = "Ejemplo 1", modifier = Modifier
            .height(55.dp)
            .background(Color.Red))
        Text(text = "Ejemplo 2", modifier = Modifier
            .height(55.dp)
            .background(Color.Black))
        Text(text = "Ejemplo 3", modifier = Modifier
            .height(55.dp)
            .background(Color.Cyan))
        Text(text = "Ejemplo 4", modifier = Modifier
            .height(55.dp)
            .background(Color.Blue))
        Text(text = "Ejemplo 4", modifier = Modifier
            .height(55.dp)
            .background(Color.Blue))

    }
}

@Composable
fun MyBox(modifier: Modifier? = null) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .background(Color.Cyan)
            .verticalScroll(
                rememberScrollState()
            ),
            contentAlignment = Alignment.Center) {
            Text(text = "Esto es un ejemplo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComponentCatalogTheme {
        MyStateExample()
    }
}