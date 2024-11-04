package com.rodriguezmauro.jetpackcomponentcatalog.recyclerview

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodriguezmauro.jetpackcomponentcatalog.R
import com.rodriguezmauro.jetpackcomponentcatalog.model.SuperHero
import kotlinx.coroutines.launch

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

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(getSuperHeroes()) { superHero ->
            ItemHero(superHero) {
                Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SuperHeroWithSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutinesScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperHeroes()) { superHero ->
                ItemHero(superHero) {
                    Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                }
            }
        }

        val showButton = remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        if (showButton.value) {
            Button(onClick = {
                coroutinesScope.launch {
                    rvState.animateScrollToItem(0)
                }
            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)) {
                Text(text = "Ir al inicio")
            }
        }
    }
}

@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier.padding(8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(getSuperHeroes()) { superHero ->
            ItemHero(superHero) {
                Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .width(200.dp)
            .clickable {
                onItemSelected(superHero)
            }
    ) {
        Column {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = superHero.superHeroName,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superHero.superHeroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superHero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(4.dp),
                fontSize = 10.sp
            )
        }
    }
}


fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero(
            "Batman",
            "Bruce Wayne",
            "DC Comics",
            R.drawable.batman
        ),
        SuperHero(
            "Daredevil",
            "Matt Murdock",
            "Marvel Comics",
            R.drawable.daredevil
        ),
        SuperHero(
            "Flash",
            "Barry Allen",
            "DC Comics",
            R.drawable.flash
        ),
        SuperHero(
            "Green Lantern",
            "Hal Jordan",
            "DC Comics",
            R.drawable.green_lantern
        ),
        SuperHero(
            "Wolverine",
            "Logan",
            "Marvel Comics",
            R.drawable.logan
        ),
        SuperHero(
            "spiderman",
            "Peter Parker",
            "Marvel Comics",
            R.drawable.spiderman
        ),
        SuperHero(
            "Thor",
            "Thor Odinson",
            "Marvel Comics",
            R.drawable.thor
        ),
        SuperHero(
            "Wonder woman",
            "Princess Diana",
            "DC Comics",
            R.drawable.wonder_woman
        ),
    )
}