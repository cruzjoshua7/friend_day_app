package com.verycool.frienddayapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.data.model.Group
import com.verycool.frienddayapp.data.model.User
import java.time.LocalDate

class UserRepositoryImpl : UserRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    private val today = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    private val nextMonth = today.plusMonths(1)

    private val dummyGroup = listOf(
        Group(1, "Group A", R.drawable.group_profile_image, "Description A"),
        Group(2, "Group B", R.drawable.group_profile_image, "Description B"),
        Group(3, "Group C", R.drawable.group_profile_image, "Description C")
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private val users = listOf(
        User(1, "Bob", R.drawable.profile_image, true, false, dummyGroup, setOf(today, nextMonth.withDayOfMonth(3))),
        User(2, "Charlie", R.drawable.profile_image, false, false, dummyGroup, setOf(today.plusDays(2), nextMonth.withDayOfMonth(5))),
        User(3, "Alice", R.drawable.profile_image, false, false, dummyGroup, setOf(today.withDayOfMonth(10), nextMonth.withDayOfMonth(15))),
        User(4, "Alice", R.drawable.profile_image, false, false, dummyGroup, setOf(today.withDayOfMonth(20), nextMonth.withDayOfMonth(8))),
        User(5, "Bob", R.drawable.profile_image, true, false, dummyGroup, setOf(today.plusDays(5), nextMonth.withDayOfMonth(12))),
        User(6, "Charlie", R.drawable.profile_image, false, false, dummyGroup, setOf(today.withDayOfMonth(7), nextMonth.withDayOfMonth(20))),
        User(7, "Alice", R.drawable.profile_image, false, false, dummyGroup, setOf(today.withDayOfMonth(17), nextMonth.withDayOfMonth(9))),
        User(8, "Bob", R.drawable.profile_image, true, false, dummyGroup, setOf(today.withDayOfMonth(25), nextMonth.withDayOfMonth(18))),
        User(9, "Charlie", R.drawable.profile_image, false, false, dummyGroup, setOf(today.withDayOfMonth(13), nextMonth.withDayOfMonth(21))),
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getUsers(): List<User> = users

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getUser(userId: Int): User? {
        return users.find { it.userId == userId }
    }


}