package com.verycool.frienddayapp.presentation.ui.composables.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.verycool.frienddayapp.presentation.ui.composables.commons.CalendarGrid
import java.time.LocalDate
import java.time.YearMonth


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyCalendarScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectedDates by remember { mutableStateOf(setOf<LocalDate>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { /* Handle calendar update */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Calendar", color = Color.White)
            }

            Button(
                onClick = {
                    val currentMonth = YearMonth.now()
                    selectedDates = selectedDates.filterNot {
                        it.year == currentMonth.year && it.month == currentMonth.month
                    }.toSet()
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Clear Month", color = Color.White)
            }

            Button(
                onClick = { selectedDates = emptySet() },
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