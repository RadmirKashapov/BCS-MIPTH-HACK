package com.bscskol.main.core.auth

import com.bscskol.main.user.entities.User
import java.util.*

interface AuthorizationService {

    fun currentUserIdOrDie(): String

    fun currentUserOrDie(): User

    fun currentUserId(): Optional<String>

    fun getUserIdFromToken(token: String?): String?

}
