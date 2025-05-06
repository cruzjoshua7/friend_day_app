package com.verycool.frienddayapp.presentation.ui.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.verycool.frienddayapp.data.model.Group
import com.verycool.frienddayapp.presentation.ui.composables.navigation.Screen
import com.verycool.frienddayapp.viewmodel.FriendDayViewModel

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: FriendDayViewModel
) {
    val user by viewModel.selectedUser.collectAsState()
    val groups by viewModel.userGroups.collectAsState()

    if (user == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { /* Handle Create Group */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE))
            ) {
                Text("Create Group", color = Color.White)
            }
            Button(
                onClick = { /* Handle Join Group */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE))
            ) {
                Text("Join Group", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(groups) { group ->
                GroupCard(
                    group = group,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun GroupCard(
    group: Group,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate(Screen.GroupCalender.withArgs(group.id.toString()))
            }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = group.groupImage,
                contentDescription = "Group Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(group.name, fontWeight = FontWeight.Bold)
                Text(group.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
}