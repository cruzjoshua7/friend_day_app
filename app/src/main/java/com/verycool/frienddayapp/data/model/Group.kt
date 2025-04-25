package com.verycool.frienddayapp.data.model

import java.time.LocalDate

data class Group(
    val id : Int,
    val name : String,
    val groupImage : Int,
    val description : String,
    val isFavorite : Boolean = false,
    var members : List<User> = emptyList(),
    var selectedDates : Set<LocalDate> = emptySet()
)
