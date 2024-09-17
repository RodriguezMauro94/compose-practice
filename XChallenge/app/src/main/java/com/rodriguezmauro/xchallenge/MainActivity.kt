package com.rodriguezmauro.xchallenge

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodriguezmauro.xchallenge.data.TweetData
import com.rodriguezmauro.xchallenge.data.UserData
import com.rodriguezmauro.xchallenge.ui.theme.XChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Tweet(getChallengeTweet())
                    }
                }
            }
        }
    }
}

@Composable
fun Tweet(tweetData: TweetData) {

}

@Preview(showBackground = true, )
@Composable
fun GreetingPreview() {
    XChallengeTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Tweet(getChallengeTweet())
        }
    }
}

fun getChallengeTweet(): TweetData = TweetData(
    user = UserData(
        userId = "AristiDevs",
        userName = "Aris",
        avatar = R.drawable.ic_profile
    ),
    text ="Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto",
    media = R.drawable.ic_profile
)