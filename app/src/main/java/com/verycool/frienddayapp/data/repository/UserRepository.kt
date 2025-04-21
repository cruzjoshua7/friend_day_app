package com.verycool.frienddayapp.data.repository

import com.verycool.frienddayapp.data.model.User

interface UserRepository {

     fun getUsers() : List<User>
     fun getUser(userId : Int) : User?

}