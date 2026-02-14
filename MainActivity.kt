package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyUberApp()
        }
    }
}

@Composable
fun MyUberApp() {
    Scaffold(
        bottomBar = { BottomBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Text(
                "Uber",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp, start = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(16.dp)
                    .background(
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(30.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        "⌕",
                        fontSize = 31.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp),
                    )
                    Text(
                        "Where to?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(90.dp)
                            .shadow(elevation = 10.dp)
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(20.dp)
                            ),
                    ) {
                        Text(
                            "\uD83D\uDCC6",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp, top = 5.dp)
                        )
                        Text(
                            "Later",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(start = 30.dp, top = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .border(
                        width = 1.dp, Color.DarkGray,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        // .fillMaxWidth()
                        .width(60.dp)
                        .height(60.dp)
                        .padding(start = 10.dp, top = 20.dp)
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {}
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        "\uD83D\uDD53",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 5.dp, top = 10.dp, end = 10.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(
                            "742 Maple Ridge Avenue SpringField",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Text(
                            "IL 62704, United State",
                            fontSize = 10.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    "Suggestions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "See all",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(90.dp)
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lamb),
                        contentDescription = "Ride",
                        modifier = Modifier
                            .size(90.dp)
                            .align(Alignment.TopEnd)

                    )
                    Text(
                        "Ride",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 10.dp)
                            .align(Alignment.BottomStart)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(90.dp)
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.elder),
                        contentDescription = "Seniors",
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(70.dp)
                            .align(Alignment.TopEnd)
                    )
                    Text(
                        "Seniors",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 10.dp)
                            .align(Alignment.BottomStart)
                    )
                }
            }
            Text(
                "Deliver with Courier",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, top = 10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .height(190.dp)
                        .width(250.dp)
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        "Send a package",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .align(Alignment.BottomStart)
                    )
                    Text(
                        "Same-day delivery to your door",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 13.dp)
                            .align(Alignment.BottomStart)

                    )
                    ImageCard()
                }
                Box(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 15.dp)
                        .height(190.dp)
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(10.dp)
                        )

                ) {
                    Text(
                        "Send items safely",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .align(Alignment.BottomStart)
                    )
                    Text(
                        "Fast and easy same-day delivery",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 13.dp)
                            .align(Alignment.BottomStart)
                    )
                    ImageCard2()

                }
                Box(
                    modifier = Modifier
                        .width(250.dp)
                        .height(190.dp)
                        .padding(start = 15.dp)
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        "Forgot something?",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .align(Alignment.BottomStart)
                    )
                    Text(
                        "Forgot your wallet? Hit courier",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 13.dp)
                            .align(Alignment.BottomStart)
                    )
                    ImageCard3()
                }
                Box(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .width(250.dp)
                        .height(190.dp)
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        "Last-minute gift?",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .align(Alignment.BottomStart)
                    )
                    Text(
                        "Send gifts across town easily",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 13.dp)
                            .align(Alignment.BottomStart)
                    )
                    ImageCard4()
                }
            }
            Spacer(modifier = Modifier.height(110.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color.DarkGray)
            ) {
            }
        }
    }
}
@Composable
fun ImageCard() {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(130.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.delivery),
                contentDescription = "Delivery",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
@Composable
fun ImageCard2() {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(130.dp),
        shape = RoundedCornerShape(10.dp)

    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.gps),
                contentDescription = "Location",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
@Composable
fun ImageCard3() {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(130.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.wallet),
                contentDescription = "Forgot something?",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
@Composable
fun ImageCard4() {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(130.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.gift),
                contentDescription = "Last-minute gift?",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
}
@Composable
fun BottomBar() {

    NavigationBar(
        containerColor = Color.Black
    ) {

        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.GridView,
                    contentDescription = "Services"
                )
            },
            label = { Text("Services") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.Receipt,
                    contentDescription = "Activity"
                )
            },
            label = { Text("Activity") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Account"
                )
            },
            label = { Text("Account") }
        )
    }
}
