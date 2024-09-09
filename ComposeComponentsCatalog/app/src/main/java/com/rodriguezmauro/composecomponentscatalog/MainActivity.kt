package com.rodriguezmauro.composecomponentscatalog

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
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
                    Column(
                        Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())) {
                        var myText by rememberSaveable {
                            mutableStateOf("")
                        }
                        MyTextField("Ejemplo de state hosting") {
                            myText = it
                        }

                        MyImage()

                        MyImageAdvanced()

                        MyIcon()

                        MyProgress()

                        MyProgressAdvance()

                        MySwitch()

                        MyCheckBox()

                        MyCheckboxWithText()

                        var stateCheckboxWithTextCompleted by rememberSaveable {
                            mutableStateOf(false)
                        }
                        MyCheckboxWithTextCompleted(CheckInfo("titulo", stateCheckboxWithTextCompleted) {
                            stateCheckboxWithTextCompleted = it
                        })

                        val myOptions = getOptions(titles = listOf("Mauro", "Raul"))
                        Column {
                            myOptions.forEach {
                                MyCheckboxWithTextCompleted(checkInfo = it)
                            }
                        }

                        MyTriStatusCheckbox()

                        MyRadioButton()

                        var stateRadioButtonsSelected by rememberSaveable {
                            mutableStateOf("")
                        }
                        MyRadioButtonList(name = stateRadioButtonsSelected) {
                            stateRadioButtonsSelected = it
                        }

                        MyCard()

                        MyBadgedBox()

                        MyDivider()

                        MyDropDownMenu()

                        BasicSlider()

                        AdvancedSlider()

                        MyRangeSlider()

                        var showAlertDialog by remember {
                            mutableStateOf(false)
                        }
                        Box(contentAlignment = Alignment.Center) {
                            Button(onClick = {
                                showAlertDialog = true
                            }) {
                                Text(text = "Mostrar dialogo")
                            }
                            MyAlertDialog(showAlertDialog) {
                                showAlertDialog = false
                                Log.d("Dialog", if (it) "True" else "False")
                            }
                        }

                        var showSimpleCustomDialog by remember {
                            mutableStateOf(false)
                        }
                        Box(contentAlignment = Alignment.Center) {
                            Button(onClick = { showSimpleCustomDialog = true }) {
                                Text(text = "Mostrar simple custom dialog")
                            }
                        }
                        MySimpleCustomDialog(
                            showSimpleCustomDialog,
                            onConfirm = {
                                showSimpleCustomDialog = false
                                Log.d("Simple Custom Dialog", "confirmado")

                            },
                            onDismiss = {
                                showSimpleCustomDialog = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MyDropDownMenu() {
    var selectedText by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val desserts = listOf("Helado", "Chocolate", "Cafe", "Frutas")

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                selectedText = it
            },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable {
                    expanded = true
                }
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach{
                DropdownMenuItem(text = { Text(text = it) }, onClick = {
                    selectedText = it
                    expanded = false
                })
            }
        }
    }
}

@Composable
fun MyDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        color = Color.Red
    )
}

@Composable
fun MyBadgedBox() {
    BadgedBox(
        modifier = Modifier.padding(16.dp),
        badge = {
            Badge(content = { Text(text = "100") },
                containerColor = Color.Blue, contentColor = Color.White)
            }
        ) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "Star")
    }
}


@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = Color.Red, contentColor = Color.Green),
        border = BorderStroke(5.dp, Color.Magenta)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 2")
            Text(text = "Ejemplo 3")
        }
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Mauro", onClick = { onItemSelected("Mauro") })
            Text(text = "Mauro")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Jorge", onClick = { onItemSelected("Jorge") })
            Text(text = "Jorge")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Roberto", onClick = { onItemSelected("Roberto") })
            Text(text = "Roberto")
        }
    }
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxSize()) {
        RadioButton(selected = false, onClick = {  }, enabled = false, colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Yellow,
            disabledSelectedColor = Color.Green
        ))
    }
}

@Composable
fun MyTriStatusCheckbox() {
    var status by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }
    
    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        TriStateCheckbox(state = status, onClick = {
            status = when(status) {
                ToggleableState.On -> ToggleableState.Indeterminate
                ToggleableState.Off -> ToggleableState.On
                ToggleableState.Indeterminate -> ToggleableState.Off
            }
        })
        Text(text = "Tri state checkbox")
    }

}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus -> status = myNewStatus }
        )
    }
}

@Composable
fun MyCheckboxWithTextCompleted(checkInfo: CheckInfo) {
    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checkInfo.selected, onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}


@Composable
fun MyCheckboxWithText() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }
    
    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }

    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Green,
            checkmarkColor = Color.Blue
        )
    )
}

@Composable
fun MySwitch() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }

    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Magenta,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Cyan,
            disabledCheckedTrackColor = Color.Yellow,
            disabledCheckedThumbColor = Color.Yellow,
            disabledUncheckedThumbColor = Color.Yellow,
            disabledUncheckedTrackColor = Color.Yellow
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeComponentsCatalogTheme {
        MyTextFieldOutlined()
    }
}

@Composable
fun MyProgressAdvance() {
    var progressStatus by rememberSaveable {
        mutableStateOf(0f)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = { progressStatus })
        Row(Modifier.fillMaxWidth()) {
            Button(onClick = { progressStatus += 0.1f }) {
                Text(text = "Incrementar")
            }
            Button(onClick = { progressStatus -= 0.1f}) {
                Text(text = "Reducir")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.Green
            )
        }
        
        Button(onClick = {
            showLoading = !showLoading
        }) {
            Text(text = "Cargar perfil")
        }
    }

}


@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = "Icono",
        tint = Color.Red
    )
}

@Composable
fun MyImageAdvanced() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "content descrip",
        alpha = 0.5f
    )
}

@Composable
fun MyTextFieldOutlined() {
    Column {
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
    Column {
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
    Column {
        TextField(value = name, onValueChange = { onValueChanged(it) })
    }
}

@Composable
fun MyText() {
    Column {
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