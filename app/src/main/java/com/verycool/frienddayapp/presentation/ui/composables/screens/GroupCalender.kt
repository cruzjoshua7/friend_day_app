package com.verycool.frienddayapp.presentation.ui.composables.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.data.model.User
import com.verycool.frienddayapp.presentation.ui.composables.commons.CalendarGrid
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GroupCalendar(
    modifier: Modifier = Modifier,
    groupId: Int = 0
) {
    var selectedDates by remember { mutableStateOf(setOf<LocalDate>()) }

    // Dummy users for now
    var users by remember {
        mutableStateOf(
            listOf(
                User(1,"Bob", R.drawable.profile_image, true,false),
                User(2,"Charlie", R.drawable.profile_image, false,false),
                User(3,"Alice", R.drawable.profile_image, false,false),
                User(4,"Alice", R.drawable.profile_image, false,false),
                User(5,"Bob", R.drawable.profile_image, true,false),
                User(6,"Charlie", R.drawable.profile_image, false,false),
                User(7,"Alice", R.drawable.profile_image, false,false),
                User(8,"Bob", R.drawable.profile_image, true,false),
                User(9,"Charlie", R.drawable.profile_image, false,false),
            )
        )
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp) // reduced height
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)) // slight overlay for text contrast
            )
            Text(
                text = "Group Name", // Replace with dynamic name later
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        CalendarGrid(
            selectedDates = selectedDates,
            onDateClick = { date ->
                selectedDates = if (selectedDates.contains(date)) {
                    selectedDates - date
                } else {
                    selectedDates + date
                }
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { /* handle add */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE))

            ) {
                Text("+ Event")
            }

            Button(
                onClick = { /* handle remove */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE))
            ) {
                Text("- Event")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Group Members",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(users) { user ->
                UserCard(
                    user = user,
                    onFavoriteToggle = { user.isFavorite = !user.isFavorite },
                    onFilterToggle = {user.isFilter = !user.isFilter}
                )
            }
        }

    }
}

@Composable
fun UserCard(
    user: User,
    onFavoriteToggle: () -> Unit,
    onFilterToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)

    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = user.profileImage),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = user.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onFavoriteToggle) {
                Image(
                    painter = painterResource(
                        if (user.isFavorite) R.drawable.filled_heart else R.drawable.empty_heart
                    ),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = onFilterToggle) {
                Image(
                    painter = painterResource(
                        if (user.isFavorite) R.drawable.filter_filled else R.drawable.filter_empty
                    ),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(
                        R.drawable.trash_icon
                    ),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GroupCalendarPreview() {

}