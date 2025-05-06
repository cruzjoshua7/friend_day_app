package com.verycool.frienddayapp.data.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Group(
    val id: Int = 0,
    val name: String = "",
    val groupImage: String = "", // Assuming URL
    val description: String = "",
    val groupOwner: Int = 0,
    val admins: List<Int> = emptyList(),
    var favoriteMemberIds: List<Int> = emptyList(),
    var members: List<User> = emptyList(),
    var selectedDateTimes: Set<LocalDateTime> = emptySet() // updated
)
