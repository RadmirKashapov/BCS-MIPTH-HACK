package com.bscskol.main.core.auth.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
class JWTAuthSecurity(override val message: String) : AuthenticationException(message)
