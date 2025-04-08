package com.verycool.frienddayapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class FriendDayViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) : ViewModel(){

}