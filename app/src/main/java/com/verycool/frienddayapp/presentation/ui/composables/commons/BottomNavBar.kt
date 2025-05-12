package com.verycool.frienddayapp.presentation.ui.composables.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.presentation.ui.composables.navigation.Screen

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentDestination: String?,
    onGroupsClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            selected = currentDestination == Screen.GroupScreen.route,
            onClick = onGroupsClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.group_logo),
                    contentDescription = "Groups",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Groups") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.DarkGray
            )

        )
        NavigationBarItem(
            selected = currentDestination == Screen.MyCalenderScreen.route,
            onClick = onCalendarClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.calender_logo),
                    contentDescription = "My Calendar",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("My Calendar") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.DarkGray
            )
        )
        NavigationBarItem(
            selected = currentDestination == Screen.ProfileScreen.route,
            onClick = onProfileClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.my_profile_icon),
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.DarkGray
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(
        currentDestination = Screen.GroupScreen.route,
        onGroupsClick = {},
        onCalendarClick = {},
        onProfileClick = {}
    )
}