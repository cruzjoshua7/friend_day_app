package com.verycool.frienddayapp.data.model

import com.verycool.frienddayapp.R
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
