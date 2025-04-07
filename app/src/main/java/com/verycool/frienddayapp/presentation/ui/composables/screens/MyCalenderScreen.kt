package com.verycool.frienddayapp.presentation.ui.composables.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyCalendarScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectedDates by remember { mutableStateOf(setOf<LocalDate>()) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek.value % 7

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Month Navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { currentMonth = currentMonth.minusMonths(1) }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous Month")
            }
            Text(
                text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            IconButton(onClick = { currentMonth = currentMonth.plusMonths(1) }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next Month")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // **Day Labels (Sunday to Saturday)**
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
            daysOfWeek.forEach { day ->
                Text(
                    text = day,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Calendar Grid with Border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RoundedCornerShape(8.dp)) // Outer border
                .padding(2.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Add empty slots for the first row
                items(firstDayOfMonth) {
                    Box(modifier = Modifier.size(40.dp))
                }

                // Add days of the month with borders
                items(daysInMonth) { day ->
                    val date = currentMonth.atDay(day + 1)
                    val isSelected = selectedDates.contains(date)

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(1.dp, Color.Gray, RectangleShape) // Individual cell borders
                            .clip(RectangleShape)
                            .background(if (isSelected) Color.LightGray else Color.Transparent)
                            .clickable {
                                selectedDates = if (isSelected) {
                                    selectedDates - date
                                } else {
                                    selectedDates + date
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${day + 1}")
                    }
                }
            }
        }

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
                    selectedDates = selectedDates.filterNot { it.year == currentMonth.year && it.month == currentMonth.month }.toSet()
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