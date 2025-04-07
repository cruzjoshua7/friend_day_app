package com.verycool.frienddayapp.presentation.ui.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.verycool.frienddayapp.R

// Data class for Group

data class Group(
    val name: String,
    val imageRes: Int,
    val description: String,
    var isFavorite: Boolean = false
)

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val groups = remember {
        mutableStateListOf(
            Group("Group A", R.drawable.group_profile_image, "Description A"),
            Group("Group B", R.drawable.group_profile_image, "Description B"),
            Group("Group C", R.drawable.group_profile_image, "Description C")
        )
    }
        Column(modifier = Modifier.fillMaxSize().padding(16.dp).statusBarsPadding()) {
            // Buttons for Create and Join Group
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

            // Groups List
            LazyColumn {
                items(groups.size) { index ->
                    GroupCard(group = groups[index], onFavoriteToggle = {
                        groups[index] = groups[index].copy(isFavorite = !groups[index].isFavorite)
                    })
                }
            }
        }

}

@Composable
fun GroupCard(group: Group, onFavoriteToggle: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Handle click */ }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = group.imageRes),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(group.name, fontWeight = FontWeight.Bold)
                Text(group.description)
            }
            IconButton(onClick = onFavoriteToggle) {
                Image(
                    painter = painterResource(if (group.isFavorite) R.drawable.filled_heart else R.drawable.empty_heart),
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onFavoriteToggle() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
}