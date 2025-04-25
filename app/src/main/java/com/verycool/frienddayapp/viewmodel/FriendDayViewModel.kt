package com.verycool.frienddayapp.viewmodel

import androidx.lifecycle.ViewModel
import com.verycool.frienddayapp.data.model.Group
import com.verycool.frienddayapp.data.model.User
import com.verycool.frienddayapp.data.repository.GroupRepository
import com.verycool.frienddayapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FriendDayViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository
) : ViewModel(){
    // User
    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser : StateFlow<User?> = _selectedUser

    fun selectUser(userId : Int){
        _selectedUser.value = userRepository.getUser(userId)
    }
    //user dates update
    fun updateSelectedDates(dates : Set<LocalDate>){
        _selectedUser.value = _selectedUser.value?.copy(selectedDates = dates)
    }
    fun clearSelectedUser(){
        _selectedUser.value = null
    }
    //Group
    private val _selectedGroup = MutableStateFlow<Group?>(null)
    val selectedGroup : StateFlow<Group?> = _selectedGroup

    fun selectGroup(groupId : Int){
        _selectedGroup.value = groupRepository.getGroup(groupId)
    }



}