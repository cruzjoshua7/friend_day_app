package com.verycool.frienddayapp.data.repository

import com.verycool.frienddayapp.data.model.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
      fun getGroup(groupId : Int) :Flow<Group?>
      suspend fun getGroupOnce(groupId: Int):Group?
      fun getAllGroups(): Flow<List<Group>>
}