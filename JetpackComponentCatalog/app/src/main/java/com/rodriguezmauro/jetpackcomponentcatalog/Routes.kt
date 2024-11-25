package com.rodriguezmauro.jetpackcomponentcatalog

sealed class Routes(val route: String) {
    data object ScreenOne : Routes("screenOneId")
    data object ScreenTwo : Routes("screenTwoId")
    data object ScreenThree : Routes("screenThreeId")
    data object ScreenFour : Routes("screenFourId/{name}") {
        fun createRoute(name: String): String = ScreenFour.route.replace("{name}", name)
    }
    data object ScreenFive : Routes("screenFiveId?age={age}") {
        fun createRoute(age: Int): String = ScreenFive.route.replace("{age}", age.toString())
    }
}