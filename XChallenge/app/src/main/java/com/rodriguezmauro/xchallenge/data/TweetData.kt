package com.rodriguezmauro.xchallenge.data

import androidx.annotation.DrawableRes

data class TweetData(
    var user: UserData,
    var text: String,
    @DrawableRes var media: Int,
)