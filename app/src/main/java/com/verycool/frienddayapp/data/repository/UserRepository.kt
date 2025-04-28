package com.verycool.frienddayapp.data.repository

import com.verycool.frienddayapp.data.model.User

interface UserRepository {
     fun getUser(userId : Int) : User?
}