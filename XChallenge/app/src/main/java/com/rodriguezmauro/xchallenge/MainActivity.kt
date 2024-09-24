package com.rodriguezmauro.xchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
    Row {
        TweetUserAvatar(
            tweetData.user,
            modifier = Modifier
        )

        TweetContent(
            tweetData,
            modifier = Modifier
                .padding(
                    top = 15.dp,
                    end = 15.dp
                )
        )
    }

    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        color = Color.LightGray
    )
}

@Composable
fun TweetSocial(tweetData: TweetData, modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 10.dp)
    ) {
        ActionSocialIcon(R.drawable.ic_chat, tweetData.comments, Color.Black)
        ActionSocialIcon(R.drawable.ic_rt, tweetData.retweets, Color.Green)
        ActionSocialIcon(R.drawable.ic_like, tweetData.likes, Color.Red)
    }
}

@Composable
fun ActionSocialIcon(@DrawableRes icon: Int, count: Int, activeColor: Color) {
    var isClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Icon(
        painter = painterResource(id = icon),
        contentDescription = "Comments",
        tint = if(isClicked) activeColor else Color.Black,
        modifier = Modifier
            .padding(end = 5.dp)
            .clickable {
                isClicked = !isClicked
            }
    )
    Text(
        text = if (isClicked) "${count + 1}" else count.toString(),
        modifier = Modifier.padding(end = 50.dp)
    )
}

@Composable
fun TweetContent(tweetData: TweetData, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        TweetHeaderUser(
            tweetData.user,
            tweetData.sincePosted
        )

        TweetText(tweetData.text)

        TweetMedia(tweetData.media)

        TweetSocial(tweetData, modifier = Modifier)
    }
}

@Composable
fun TweetMedia(media: Int) {
    Image(
        painter = painterResource(id = media),
        contentDescription = "media",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(
                RoundedCornerShape(20.dp)
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TweetText(text: String) {
    Text(text = text, modifier = Modifier.padding(bottom = 10.dp))
}

@Composable
fun TweetHeaderUser(user: UserData, sincePosted: String) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
    ) {
        val (userName, userId, sincePostedId, actions) = createRefs()
        Text(
            text = user.userName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(userName) {
                start.linkTo(parent.start)
            })
        Text(text = user.userId,
            Modifier
                .padding(start = 5.dp)
                .constrainAs(userId) {
                    start.linkTo(userName.end)
                })
        Text(text = sincePosted,
            Modifier
                .padding(start = 5.dp)
                .constrainAs(sincePostedId) {
                    start.linkTo(userId.end)
                })
        Text(text = "...", modifier = Modifier.constrainAs(actions) {
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun TweetUserAvatar(user: UserData, modifier: Modifier) {
    Image(
        painter = painterResource(id = user.avatar),
        contentDescription = user.userName,
        modifier = modifier
            .padding(15.dp)
            .width(60.dp)
            .height(60.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = Color.Black, shape = CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun TweetPreview() {
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
        userId = "@AristiDevs",
        userName = "Aris",
        avatar = R.drawable.ic_profile
    ),
    sincePosted = "4h",
    text = "Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto",
    media = R.drawable.ic_profile,
    comments = 1,
    retweets = 1,
    likes = 0
)