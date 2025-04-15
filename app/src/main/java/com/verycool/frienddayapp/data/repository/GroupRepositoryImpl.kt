package com.verycool.frienddayapp.data.repository

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.data.model.Group
import com.verycool.frienddayapp.data.model.User

class GroupRepositoryImpl : GroupRepository {
    override  fun getGroups(): List<Group> {
        return listOf(
            Group(1, "Group A", R.drawable.group_profile_image, "Description A"),
            Group(2, "Group B", R.drawable.group_profile_image, "Description B"),
            Group(3, "Group C", R.drawable.group_profile_image, "Description C")
        )
    }
}