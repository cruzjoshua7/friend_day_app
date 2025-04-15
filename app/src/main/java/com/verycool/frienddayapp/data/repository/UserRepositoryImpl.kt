package com.verycool.frienddayapp.data.repository

import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.data.model.User

class UserRepositoryImpl : UserRepository {
    override  fun getUsers(): List<User> {
        return listOf(
            User(1,"Bob", R.drawable.profile_image, true,false),
            User(2,"Charlie", R.drawable.profile_image, false,false),
            User(3,"Alice", R.drawable.profile_image, false,false),
            User(4,"Alice", R.drawable.profile_image, false,false),
            User(5,"Bob", R.drawable.profile_image, true,false),
            User(6,"Charlie", R.drawable.profile_image, false,false),
            User(7,"Alice", R.drawable.profile_image, false,false),
            User(8,"Bob", R.drawable.profile_image, true,false),
            User(9,"Charlie", R.drawable.profile_image, false,false),
        )
    }
}