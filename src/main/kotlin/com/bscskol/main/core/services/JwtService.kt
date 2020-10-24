package com.bscskol.main.core.services

import com.bscskol.main.user.entities.User

interface JwtService {

    fun getToken(user: User): String

}
