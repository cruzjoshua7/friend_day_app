package com.verycool.frienddayapp.data.repository

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.data.model.Group
import com.verycool.frienddayapp.data.model.User
import java.time.LocalDate


class GroupRepositoryImpl : GroupRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override  fun getGroups(): List<Group> = groups

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getGroup(groupId : Int): Group? {
        return groups.find { it.id == groupId }
    }
}

private val members1 = listOf(
    User(1, "Bob", R.drawable.profile_image, true, false),
    User(2, "Charlie", R.drawable.profile_image, false, false),
    User(3, "Alice", R.drawable.profile_image, false, false),
    User(4, "Alice", R.drawable.profile_image, false, false),
    User(5, "Bob", R.drawable.profile_image, true, false),
    User(6, "Charlie", R.drawable.profile_image, false, false),
    User(7, "Alice", R.drawable.profile_image, false, false),
    User(8, "Bob", R.drawable.profile_image, true, false),
    User(9, "Josh", R.drawable.profile_image, false, false)
)

private val members = listOf(
    User(1, "Bob", R.drawable.profile_image, true, false),
    User(2, "Charlie", R.drawable.profile_image, false, false),
    User(3, "Alice", R.drawable.profile_image, false, false),
    User(4, "Alice", R.drawable.profile_image, false, false),
    User(5, "Bob", R.drawable.profile_image, true, false),
    User(6, "marlley", R.drawable.profile_image, false, false),
    )

@RequiresApi(Build.VERSION_CODES.O)
private val todaygroup = LocalDate.now()
@RequiresApi(Build.VERSION_CODES.O)
private val nextMonthGroup = todaygroup.plusMonths(1)

@SuppressLint("NewApi")
private val groups = listOf(
    Group(1, "Group A", R.drawable.group_profile_image, "Description A", true, members,setOf(todaygroup, nextMonthGroup.withDayOfMonth(3))),
    Group(2, "Group B", R.drawable.group_profile_image, "Description B", true, members1, setOf(todaygroup.plusDays(2), nextMonthGroup.withDayOfMonth(5))),
    Group(3, "Group C", R.drawable.group_profile_image, "Description C", true, members, setOf(todaygroup.withDayOfMonth(10), nextMonthGroup.withDayOfMonth(15)))
)



