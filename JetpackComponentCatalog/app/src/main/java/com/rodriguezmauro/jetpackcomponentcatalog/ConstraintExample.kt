package com.rodriguezmauro.jetpackcomponentcatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintExample1() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        var (boxRed, boxBlue, boxYellow, boxMagenta, boxGreen) = createRefs()

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxRed.bottom)
                start.linkTo(boxRed.end)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                bottom.linkTo(boxRed.top)
                end.linkTo(boxRed.start)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                start.linkTo(boxRed.end)
                bottom.linkTo(boxRed.top)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                end.linkTo(boxRed.start)
                top.linkTo(boxRed.bottom)
            })
    }
}

@Composable
fun ConstraintExampleGuide() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        //val startGuide = createGuidelineFromTop(0.1f)
        val boxRed = createRef()
        val topGuide = createGuidelineFromTop(0.1f)
        val startGuide = createGuidelineFromStart(0.25f)
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            })
    }
}

@Composable
fun ConstraintBarrier() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (boxRed, boxGreen, boxYellow) = createRefs()
        val barrier = createEndBarrier(boxRed, boxGreen)

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(parent.start, margin = 16.dp)
            })

        Box(modifier = Modifier
            .size(235.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(boxGreen.bottom)
                start.linkTo(parent.start, margin = 32.dp)
            })

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(barrier)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintChainExample() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (boxRed, boxGreen, boxYellow, boxBlue, boxMagenta) = createRefs()

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(parent.start)
                end.linkTo(boxRed.start)
                top.linkTo(parent.top)
                bottom.linkTo(boxBlue.top)
            })

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(boxGreen.end)
                end.linkTo(boxYellow.start)
            })

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(boxRed.end)
                end.linkTo(parent.end)
            })

        createHorizontalChain(boxGreen, boxRed, boxYellow, chainStyle = ChainStyle.SpreadInside)

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxGreen.bottom)
                bottom.linkTo(boxMagenta.top)
            })

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                top.linkTo(boxBlue.bottom)
                bottom.linkTo(parent.bottom)
            })

        createVerticalChain(boxGreen, boxBlue, boxMagenta, chainStyle = ChainStyle.SpreadInside)
    }
}