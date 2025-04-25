package com.verycool.frienddayapp.presentation.ui.composables.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.presentation.ui.composables.commons.BottomNavBar
import com.verycool.frienddayapp.presentation.ui.composables.screens.GroupCalendar
import com.verycool.frienddayapp.presentation.ui.composables.screens.GroupScreen
import com.verycool.frienddayapp.presentation.ui.composables.screens.LoginScreen
import com.verycool.frienddayapp.presentation.ui.composables.screens.MyCalendarScreen
import com.verycool.frienddayapp.presentation.ui.composables.screens.ProfileScreen
import com.verycool.frienddayapp.viewmodel.FriendDayViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination?.route

    val viewModel : FriendDayViewModel = hiltViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentDestination != Screen.LoginScreen.route) {
                BottomNavBar(
                    currentDestination = currentDestination, // 👈 pass it here
                    onGroupsClick = {
                        navController.navigate(Screen.GroupScreen.route) {
                            popUpTo(Screen.GroupScreen.route) { inclusive = true }
                        }
                    },
                    onCalendarClick = {
                        navController.navigate(Screen.MyCalenderScreen.route) {
                            popUpTo(Screen.MyCalenderScreen.route) { inclusive = true }
                        }
                    },
                    onProfileClick = {
                        navController.navigate(Screen.ProfileScreen.route) {
                            popUpTo(Screen.ProfileScreen.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            NavHost(
                navController = navController,
                startDestination = Screen.LoginScreen.route
            ) {
                composable(route = Screen.LoginScreen.route) {
                    LoginScreen(navController = navController, viewModel = viewModel)
                }
                composable(route = Screen.GroupScreen.route) {
                    GroupScreen(navController = navController, viewModel = viewModel)
                }
                composable(route = Screen.MyCalenderScreen.route) {
                    MyCalendarScreen(viewModel = viewModel)
                }
                composable(route = Screen.ProfileScreen.route) {
                    ProfileScreen(navController = navController, viewModel = viewModel)
                }
                composable(
                    route = Screen.GroupCalender.route + "/{groupId}",
                    arguments = listOf(
                        navArgument("groupId"){
                            type = NavType.IntType
                            defaultValue = 0
                            nullable = false
                        }
                    )
                ){ entry ->
                    GroupCalendar(groupId = entry.arguments?.getInt("groupId")?:0, viewModel = viewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewNavigation(){
    Navigation()
}