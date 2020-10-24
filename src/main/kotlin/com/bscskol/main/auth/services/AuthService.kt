package com.bscskol.main.auth.services

import com.bscskol.main.auth.vo.TokenVO
import com.bscskol.main.auth.vo.UserCreateRq
import com.bscskol.main.auth.vo.UserLoginRq

interface AuthService {

    fun createUser(user: UserCreateRq): TokenVO
    fun loginUser(user: UserLoginRq): TokenVO
    fun updateToken(token: String): TokenVO

}
