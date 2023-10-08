package com.example.assignment_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.assignment_3.ui.theme.Assignment_3Theme
import com.example.assignment_3.ui.theme.GreenShade1
import com.example.assignment_3.ui.theme.Purple80
import com.example.assignment_3.utils.randomSampleImageUrl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment_3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserProfile("Mark Worms", randomSizedPhotos)
                }
            }
        }
    }
}

@Composable
fun UserProfile(name: String, data: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val horizontalBackgroundGuideLine = createGuidelineFromTop(0.85f)
            val topGuideLine = createGuidelineFromTop(40.dp)
            val startGuideLine = createGuidelineFromStart(20.dp)
            val endGuideLine = createGuidelineFromEnd(20.dp)
            val (greenBackground, backArrow, hamburger, headingText, profileCard, profileImage, scrollableCards) = createRefs()

            Image(
                painterResource(id = R.drawable.ic_green_rectangle),
                contentDescription = "Background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.constrainAs(greenBackground) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(horizontalBackgroundGuideLine)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
            )

            createHorizontalChain(
                backArrow,
                headingText,
                hamburger,
                chainStyle = ChainStyle.SpreadInside
            )
            Icon(
                modifier = Modifier
                    .constrainAs(backArrow) {
                        top.linkTo(topGuideLine)
                    }
                    .size(30.dp)
                    .padding(start = 5.dp),
                painter = rememberVectorPainter(image = Icons.Filled.KeyboardArrowLeft),
                contentDescription = "",
                tint = Color.White
            )
            Text(
                modifier = Modifier.constrainAs(headingText) {
                    top.linkTo(backArrow.top)
                    bottom.linkTo(backArrow.bottom)
                },
                text = stringResource(id = R.string.profile),
                style = MaterialTheme.typography.titleLarge.copy(Color.White)
            )
            Icon(
                modifier = Modifier
                    .constrainAs(hamburger) {
                        top.linkTo(backArrow.top)
                        bottom.linkTo(backArrow.bottom)
                    }
                    .size(30.dp)
                    .padding(end = 5.dp),
                painter = rememberVectorPainter(image = Icons.Filled.MoreVert),
                contentDescription = "",
                tint = Color.White
            )

            Card(
                modifier = Modifier
                    .constrainAs(profileCard) {
                        top.linkTo(headingText.bottom, 80.dp)
                        start.linkTo(startGuideLine)
                        end.linkTo(endGuideLine)
                    }
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .height(230.dp),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(5.dp),
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (profileName, followButton, location) = createRefs()
                    Text(
                        text = name,
                        modifier = Modifier
                            .constrainAs(profileName) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(top = 80.dp),
                        style = MaterialTheme.typography.titleLarge.copy(Color.Black)
                    )

                    val annotatedString = buildAnnotatedString {
                        appendInlineContent(id = "imageId")
                        append(stringResource(id = R.string.place_name))
                    }
                    val inlineContentMap = mapOf(
                        "imageId" to InlineTextContent(
                            Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
                        ) {
                            Image(
                                imageVector = Icons.Filled.LocationOn,
                                modifier = Modifier.fillMaxSize(),
                                contentDescription = ""
                            )
                        }
                    )

                    Text(
                        annotatedString, inlineContent = inlineContentMap,
                        style = MaterialTheme.typography.bodyMedium.copy(Color.Black),
                        modifier = Modifier.constrainAs(location) {
                            top.linkTo(profileName.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )

                    Text(text = stringResource(id = R.string.follow),
                        style = MaterialTheme.typography.titleLarge.copy(Color.White),
                        modifier = Modifier
                            .padding(15.dp)
                            .background(GreenShade1, shape = RoundedCornerShape(30.dp))
                            .padding(start = 35.dp, end = 35.dp, top = 12.dp, bottom = 12.dp)
                            .constrainAs(followButton) {
                                top.linkTo(location.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )
                }

            }

            Image(
                modifier = Modifier
                    .size(120.dp)
                    .constrainAs(profileImage) {
                        top.linkTo(profileCard.top)
                        bottom.linkTo(profileCard.top)
                        start.linkTo(startGuideLine)
                        end.linkTo(endGuideLine)
                    }
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_profile_pic),
                contentDescription = null,
            )


            /*val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .constrainAs(scrollableCards) {
                top.linkTo(profileCard.bottom, margin = 20.dp)
            }
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Statistics()
        FriendsList()
        FriendsList()
    }*/
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Statistics()
            }
            item {
                FriendsList()
            }
            item {
                StaggeredPhotosList(data)
            }
        }
    }
}

@Composable
fun Statistics() {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        val statsNumList = listOf("456", "23.5K", "3.6M")
        val statsCatList = listOf("Following", "Followers", "Like")
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 25.dp, end = 25.dp)
        ) {
            val (numberWithText, divider1, numberWithText1, divider2, numberWithText2) = createRefs()

            createHorizontalChain(
                numberWithText,
                divider1,
                numberWithText1,
                divider2,
                numberWithText2,
                chainStyle = ChainStyle.SpreadInside
            )
            NumberAndText(
                number = statsNumList[0], category = statsCatList[0],
                Modifier.constrainAs(numberWithText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            Divider(
                color = Purple80,
                modifier = Modifier
                    .constrainAs(divider1) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .height(60.dp)
                    .width(2.dp)
            )
            NumberAndText(
                number = statsNumList[1], category = statsCatList[1],
                Modifier.constrainAs(numberWithText1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            Divider(
                color = Purple80,
                modifier = Modifier
                    .constrainAs(divider2) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .height(60.dp)
                    .width(2.dp)
            )
            NumberAndText(
                number = statsNumList[2], category = statsCatList[2],
                Modifier.constrainAs(numberWithText2) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    }
}

@Composable
fun NumberAndText(number: String, category: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = number, style = MaterialTheme.typography.titleLarge.copy(Color.Black)
        )
        Text(
            text = category, style = MaterialTheme.typography.bodyMedium.copy(Color.Gray)
        )
    }
}

@Composable
fun FriendsList() {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(170.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            val (friendsText, seeAllText, picWithName) = createRefs()

            createHorizontalChain(
                friendsText,
                seeAllText,
                chainStyle = ChainStyle.SpreadInside
            )
            Text(
                modifier = Modifier
                    .constrainAs(friendsText) {
                        top.linkTo(parent.top, 25.dp)
                    },
                text = stringResource(id = R.string.friends),
                style = MaterialTheme.typography.titleLarge.copy(Color.Black)

            )
            Text(
                modifier = Modifier
                    .constrainAs(seeAllText) {
                        top.linkTo(friendsText.top)
                        bottom.linkTo(friendsText.bottom)
                    },
                text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.bodyMedium.copy(Color.Gray)
            )

            LazyRow(
                Modifier.constrainAs(picWithName) {
                    top.linkTo(friendsText.bottom, 20.dp)
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                repeat(5) {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_profile_pic),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(50.dp)
                                    .clip(CircleShape)
                            )
                            Text(
                                text = stringResource(id = R.string.friend_name),
                                style = MaterialTheme.typography.bodyMedium.copy(Color.Black)
                            )
                        }
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StaggeredPhotosList(data: List<String>) {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
            .fillMaxWidth()
            .height(300.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 20.dp)
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.photos),
                style = MaterialTheme.typography.titleLarge.copy(Color.Black)
            )
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(120.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(data.size) { index ->
                        AsyncImage(
                            data[index],
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            contentScale = ContentScale.Crop
                        )
                    }
                },
            )
        }
    }
}

private val randomSizedPhotos = listOf(
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment_3Theme {
        UserProfile("Mark Worms", randomSizedPhotos)
    }
}