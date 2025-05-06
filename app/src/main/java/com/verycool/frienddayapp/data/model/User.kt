package com.verycool.frienddayapp.data.model

data class User(
    val uid: String = "", // Firebase UID
    val userId: Int = 0,
    val name: String = "",
    val email: String = "",
    val profileImage: String = "", // Assuming URL
    var favoriteGroupIds: List<Int> = emptyList(),
    var userGroupIds: List<Int> = emptyList(),
    val userGroups: List<Group> = emptyList(),
    var selectedDateTimes: List<String> = emptyList()
)
