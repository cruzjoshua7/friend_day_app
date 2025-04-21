package com.verycool.frienddayapp.data.model

import java.time.LocalDate

data class User(
    val userId : Int,
    val name :String,
    val profileImage : Int,
    var isFavorite : Boolean,
    var isFilter : Boolean,
    var userGroups : List<Group> = emptyList(),
    var selectedDates : Set<LocalDate> = emptySet()
)
