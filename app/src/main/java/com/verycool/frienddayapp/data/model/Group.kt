package com.verycool.frienddayapp.data.model

import java.time.LocalDateTime

data class Group(
    val id: Int = 0,
    val name: String = "",
    val groupImage: String = "",
    val description: String = "",
    val groupOwner: Int = 0,
    val admins: List<Int> = emptyList(),
    var favoriteMemberIds: List<Int> = emptyList(),
    var members: List<User> = emptyList(),
    var selectedDateTimes: Set<LocalDateTime> = emptySet()
)
