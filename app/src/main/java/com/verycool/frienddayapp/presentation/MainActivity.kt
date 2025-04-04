package com.verycool.frienddayapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.presentation.ui.composables.commons.BottomNavBar
import com.verycool.frienddayapp.presentation.ui.composables.screens.HomeScreen
import com.verycool.frienddayapp.presentation.ui.composables.screens.LoginScreen
import com.verycool.frienddayapp.presentation.ui.composables.screens.MyCalendarScreen
import com.verycool.frienddayapp.presentation.ui.composables.screens.ProfileScreen
import com.verycool.frienddayapp.presentation.ui.theme.FriendDayAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FriendDayAppTheme {
                // Manage the current screen and whether to show the BottomNavBar
                var currentScreen by remember { mutableStateOf("Profile") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentScreen != "Login") {
                            BottomNavBar(
                                onGroupsClick = { /* Handle Groups Click */ },
                                onCalendarClick = { /* Handle Calendar Click */ },
                                onProfileClick = { /* Handle Profile Click */ }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Background Image
                        Image(
                            painter = painterResource(id = R.drawable.background), // Your drawable
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop // Ensures the image fills the screen properly
                        )

                        // Show different screens based on the current screen state
                        when (currentScreen) {
                            "Home" -> HomeScreen(modifier = Modifier.padding(innerPadding).statusBarsPadding())
                            "Login" -> LoginScreen(modifier = Modifier.padding(innerPadding).statusBarsPadding())
                            "MyCalender" -> MyCalendarScreen(modifier = Modifier.padding(innerPadding).statusBarsPadding())
                            "Profile" -> ProfileScreen(modifier = Modifier.padding(innerPadding).statusBarsPadding())

                        // Add other screens as needed
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FriendDayAppTheme {

    }
}