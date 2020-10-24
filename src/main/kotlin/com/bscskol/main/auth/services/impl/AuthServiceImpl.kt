package com.bscskol.main.auth.services.impl

import com.bscskol.main.auth.vo.UserCreateRq
import com.bscskol.main.auth.vo.UserLoginRq
import com.bscskol.main.auth.services.AuthService
import com.bscskol.main.auth.vo.TokenVO
import com.bscskol.main.core.auth.JWTDecoder
import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.services.impl.JwtServiceImpl
import com.bscskol.main.user.entities.User
import com.bscskol.main.user.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
        private val userService: UserService,
        private val jwtService: JwtServiceImpl,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val jwtDecoder: JWTDecoder
) : AuthService {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Throws(EntityNotFoundException::class)
    override fun loginUser(user: UserLoginRq): TokenVO {
        val foundUser = userService.getUserByEmailOrThrow(user.email)

        if (foundUser.password == null)
            throw BadCredentialsException("A password is empty")

        if (!bCryptPasswordEncoder.matches(user.password, foundUser.password))
            throw BadCredentialsException("Passwords do not match")

        return jwtService.getToken(foundUser).let(::toTokenVO)
    }

    override fun updateToken(token: String): TokenVO {
        val jwt = jwtDecoder.decodeWithSubject(token)

        return jwtService.getToken(userService.findByIdOrThrow(jwt.subject)).let(::toTokenVO)
    }

    override fun createUser(user: UserCreateRq): TokenVO {
        val id = userService.save(User().apply {
            email = user.email
            password = bCryptPasswordEncoder.encode(user.password)
        }).id ?: throw IllegalArgumentException("Bad id returned.")

        log.debug("Created entity $id")

        return jwtService.getToken(userService.findByIdOrThrow(id)).let(::toTokenVO)
    }

    private fun toTokenVO(token: String): TokenVO =
            TokenVO(token)

}
