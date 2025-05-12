package com.verycool.frienddayapp.data.repository

import com.verycool.frienddayapp.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

     suspend fun updateUserSelectedDates(uid:String, dates:List<String>)
     suspend fun getUserByUid(uid : String):Flow<User?>
     fun getUser(userId:Int):Flow<User?>
     fun getAllUsers(): Flow<List<User>>
}