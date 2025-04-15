package com.verycool.frienddayapp.data.repository

import com.verycool.frienddayapp.data.model.Group

interface GroupRepository {
     fun getGroups() : List<Group>
}