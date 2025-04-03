package com.verycool.frienddayapp.presentation.ui.composables.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.verycool.frienddayapp.R

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    onGroupsClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            selected = false, // Set the selection state
            onClick = onGroupsClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.group_logo), // Replace with your drawable
                    contentDescription = "Groups",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Groups") }
        )
        NavigationBarItem(
            selected = false,
            onClick = onCalendarClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.calender_logo), // Replace with your drawable
                    contentDescription = "My Calendar",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("My Calendar") }
        )
        NavigationBarItem(
            selected = false,
            onClick = onProfileClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.profile_image), // Replace with your drawable
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Profile") }
        )
    }
}