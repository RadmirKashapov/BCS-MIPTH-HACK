package com.bscskol.main.core.services.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.bscskol.main.core.configuration.SecurityConstants
import com.bscskol.main.user.entities.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtServiceImpl {

    @Value("\${jwt.signing-key}")
    lateinit var signingKey: String

    private fun createAccessToken(user: User): String {
        val issuedAt = Date()
        val calendar = Calendar.getInstance()
        calendar.time = issuedAt
        calendar.add(Calendar.MILLISECOND, SecurityConstants.EXPIRATION_TIME)

        return JWT.create()
                .withSubject(user.id.toString())
                .withClaim("firstName", user.firstName)
                .withClaim("lastName", user.lastName)
                .withClaim("roles", user.role.toString())
                .withIssuer(SecurityConstants.TOKEN_ISSUER)
                .withIssuedAt(issuedAt)
                .withExpiresAt(calendar.time)
                .sign(Algorithm.HMAC512(signingKey))
    }

    fun getToken(user: User): String =
            createAccessToken(user)

}
