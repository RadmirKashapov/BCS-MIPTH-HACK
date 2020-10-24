package com.bscskol.main.user.repositories

import com.bscskol.main.core.repositories.BaseRepository
import com.bscskol.main.user.entities.User

interface UserRepository : BaseRepository<User> {

    fun findByEmail(email: String): User?

}
