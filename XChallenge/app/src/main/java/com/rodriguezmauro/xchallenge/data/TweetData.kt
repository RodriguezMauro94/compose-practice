package com.rodriguezmauro.xchallenge.data

import androidx.annotation.DrawableRes

data class TweetData(
    var user: UserData,
    var sincePosted: String,
    var text: String,
    @DrawableRes var media: Int,
    var comments: Int,
    var retweets: Int,
    var likes: Int
)