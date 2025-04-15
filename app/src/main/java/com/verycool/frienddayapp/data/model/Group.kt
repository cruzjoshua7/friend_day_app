package com.verycool.frienddayapp.data.model

import com.verycool.frienddayapp.R

data class Group(
    val id : Int,
    val name : String,
    val groupImage : Int,
    val description : String,
    val isFavorite : Boolean = false
)
