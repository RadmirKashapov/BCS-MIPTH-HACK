package com.bscskol.main.core.services.impl

import com.bscskol.main.user.services.UserService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
        private val userService: UserService
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.getUserByEmailOrThrow(username)

        return User(user.email, user.password, listOf(SimpleGrantedAuthority(user.role.toString())))
    }

}
