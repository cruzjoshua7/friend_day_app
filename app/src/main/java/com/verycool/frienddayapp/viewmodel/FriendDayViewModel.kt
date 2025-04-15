package com.verycool.frienddayapp.viewmodel

import androidx.lifecycle.ViewModel
import com.verycool.frienddayapp.data.repository.GroupRepository
import com.verycool.frienddayapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class FriendDayViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository
) : ViewModel(){

    private val _groupList = groupRepository.getGroups()
    val groupList = _groupList

    private val _userList = userRepository.getUsers()
    val userList = _userList

}