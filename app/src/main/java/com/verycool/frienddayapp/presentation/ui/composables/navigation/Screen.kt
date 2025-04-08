package com.verycool.frienddayapp.presentation.ui.composables.navigation

sealed class Screen(val route : String) {
    object GroupScreen : Screen("group_screen")
    object LoginScreen : Screen("login_screen")
    object MyCalenderScreen : Screen("my_calender_screen")
    object ProfileScreen : Screen("profile_screen")


}