package com.verycool.frienddayapp.presentation.ui.composables.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.verycool.frienddayapp.presentation.ui.composables.commons.CalendarGrid
import com.verycool.frienddayapp.viewmodel.FriendDayViewModel
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyCalendarScreen(
    modifier: Modifier = Modifier,
    viewModel: FriendDayViewModel
) {
    val user by viewModel.selectedUser.collectAsState()
    val selectedDateTimes = user?.selectedDateTimes.orEmpty()

    val selectedDateTimeObjects = remember(selectedDateTimes) {
        selectedDateTimes.mapNotNull {
            try {
                LocalDateTime.parse(it)
            } catch (e: Exception) {
                null
            }
        }.toSet()
    }

    val selectedDates = remember(selectedDateTimeObjects) {
        selectedDateTimeObjects.map { it.toLocalDate() }.toSet()
    }

    if (user == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CalendarGrid(
            selectedDates = selectedDates,
            onDateClick = { date ->
                val updatedDateTimes = if (selectedDates.contains(date)) {
                    selectedDateTimeObjects.filterNot { it.toLocalDate() == date }.toSet()
                } else {
                    selectedDateTimeObjects + setOf(date.atStartOfDay())
                }
                viewModel.updateSelectedDates(updatedDateTimes)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.saveSelectedDates() },
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Calendar", color = Color.White)
            }

            Button(
                onClick = {
                    viewModel.updateSelectedDates(emptySet())
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reset Calendar", color = Color.White)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewCalendarScreen() {
}