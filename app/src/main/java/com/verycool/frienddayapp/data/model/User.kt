package com.verycool.frienddayapp.data.model

data class User(
    val uid: String = "",
    val userId: Int = 0,
    val name: String = "",
    val email: String = "",
    val profileImage: String = "",
    var favoriteGroupIds: List<Int> = emptyList(),
    var userGroupIds: List<Int> = emptyList(),
    var selectedDateTimes: List<String> = emptyList()
)
