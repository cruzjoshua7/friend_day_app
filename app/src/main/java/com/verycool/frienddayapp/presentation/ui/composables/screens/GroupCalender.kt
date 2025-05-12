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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import coil.compose.AsyncImage
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.data.model.User
import com.verycool.frienddayapp.presentation.ui.composables.commons.CalendarGrid
import com.verycool.frienddayapp.viewmodel.FriendDayViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GroupCalendar(
    modifier: Modifier = Modifier,
    groupId: Int = 0,
    viewModel: FriendDayViewModel
) {
    LaunchedEffect(groupId) {
        viewModel.selectGroup(groupId)
    }

    val group by viewModel.selectedGroup.collectAsState()

    var selectedDates by remember { mutableStateOf(setOf<LocalDate>()) }

    LaunchedEffect(group) {
        selectedDates = group?.selectedDateTimes
            ?.map { it.toLocalDate() }
            ?.toSet() ?: emptySet()
    }

    val members = group?.members ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Box(modifier = Modifier.matchParentSize().background(Color.Black.copy(alpha = 0.3f)))
            Text(
                text = group?.name ?: "Group",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE))
            ) {
                Text("+ Event")
            }

            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE))
            ) {
                Text("- Event")
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Group Members", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(members) { user ->
                UserCard(
                    user = user,
//                    onFavoriteToggle = { user.isFavorite = !user.isFavorite },
//                    onFilterToggle = { user.isFilter = !user.isFilter }
                )
            }
        }
    }
}


@Composable
fun UserCard(
    user: User,
//    onFavoriteToggle: () -> Unit,
//    onFilterToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = user.profileImage,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = user.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
//            IconButton(onClick = onFavoriteToggle) {
//                Image(
//                    painter = painterResource(
//                        if (user.isFavorite) R.drawable.filled_heart else R.drawable.empty_heart
//                    ),
//                    contentDescription = "Favorite",
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//            IconButton(onClick = onFilterToggle) {
//                Image(
//                    painter = painterResource(
//                        if (user.isFilter) R.drawable.filter_filled else R.drawable.filter_empty
//                    ),
//                    contentDescription = "Filter",
//                    modifier = Modifier.size(24.dp)
//                )
//            }
            IconButton(onClick = { /* TODO: delete */ }) {
                Image(
                    painter = painterResource(R.drawable.trash_icon),
                    contentDescription = "Delete",
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