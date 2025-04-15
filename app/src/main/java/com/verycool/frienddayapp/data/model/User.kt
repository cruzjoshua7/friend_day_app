package com.verycool.frienddayapp.data.model

data class User(
    val userId : Int,
    val name :String,
    val profileImage : Int,
    var isFavorite : Boolean,
    var isFilter : Boolean
)
