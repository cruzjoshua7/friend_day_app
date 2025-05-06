package com.verycool.frienddayapp.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.verycool.frienddayapp.data.model.Group
import com.verycool.frienddayapp.data.model.User
import com.verycool.frienddayapp.data.repository.GroupRepository
import com.verycool.frienddayapp.data.repository.UserRepository
import com.verycool.frienddayapp.domain.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class FriendDayViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser

    private val _userGroups = MutableStateFlow<List<Group>>(emptyList())
    val userGroups: StateFlow<List<Group>> = _userGroups

    private val _selectedGroup = MutableStateFlow<Group?>(null)
    val selectedGroup: StateFlow<Group?> = _selectedGroup

    fun loginUser(email: String, password: String) {
        _loginState.value = LoginState.Loading

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = firebaseAuth.currentUser?.uid
                    if (uid != null) {
                        viewModelScope.launch(dispatcher) {
                            userRepository.getUserByUid(uid)
                                .distinctUntilChanged()
                                .collect { user ->
                                    if (user != null) {
                                        _selectedUser.value = user
                                        _userGroups.value = fetchUserGroups(user)
                                        _loginState.value = LoginState.Success
                                        Log.d("FriendDay", "User loaded: ${user.name}")
                                    } else {
                                        _loginState.value = LoginState.Error("User data not found in Firestore.")
                                    }
                                }
                        }
                    } else {
                        _loginState.value = LoginState.Error("User ID not found.")
                    }
                } else {
                    _loginState.value = LoginState.Error(task.exception?.message ?: "Login failed")
                }
            }
    }

    fun logout() {
        firebaseAuth.signOut()
        _loginState.value = LoginState.Idle
        _selectedUser.value = null
        _userGroups.value = emptyList()
        _selectedGroup.value = null
    }

    fun selectUser(userId: Int) {
        viewModelScope.launch(dispatcher) {
            userRepository.getUser(userId).collect { user ->
                if (user != null) {
                    _selectedUser.value = user
                    _userGroups.value = fetchUserGroups(user)
                }
            }
        }
    }

    private suspend fun fetchUserGroups(user: User): List<Group> {
        return user.userGroupIds.mapNotNull { groupId ->
            try {
                groupRepository.getGroupOnce(groupId)
            } catch (e: Exception) {
                Log.w("FriendDay", "Failed to fetch group $groupId", e)
                null
            }
        }
    }

    fun updateSelectedDates(dates: Set<LocalDateTime>) {
        _selectedUser.value = _selectedUser.value?.copy(selectedDateTimes = dates.map { it.toString() })
    }

    fun saveSelectedDates() {
        val user = _selectedUser.value ?: return
        viewModelScope.launch(dispatcher) {
            userRepository.updateUserSelectedDates(user.uid, user.selectedDateTimes)
        }
    }

    fun selectGroup(groupId: Int) {
        viewModelScope.launch(dispatcher) {
            groupRepository.getGroup(groupId).collect { group ->
                _selectedGroup.value = group
            }
        }
    }
}