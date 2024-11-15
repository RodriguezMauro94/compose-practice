package com.rodriguezmauro.jetpackcomponentcatalog

sealed class Routes(val route: String) {
    data object ScreenOne: Routes("screenOneId")
    data object ScreenTwo: Routes("screenTwoId")
    data object ScreenThree: Routes("screenThreeId")
    data object ScreenFour: Routes("screenFourId/{name}")
}