package com.rodriguezmauro.xchallenge.data

import androidx.annotation.DrawableRes

data class UserData(
    var userId: String,
    var userName: String,
    @DrawableRes var avatar: Int
)