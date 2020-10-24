package com.bscskol.main.auth.controllers

import com.bscskol.main.auth.services.AuthService
import com.bscskol.main.auth.vo.TokenVO
import com.bscskol.main.auth.vo.UserCreateRq
import com.bscskol.main.auth.vo.UserLoginRq
import com.bscskol.main.core.controllers.BaseController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/auth")
class AuthController : BaseController() {

    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/login")
    fun login(@RequestBody userLoginRq: UserLoginRq): ResponseEntity<TokenVO> = processServiceExceptions {
        ResponseEntity.ok(authService.loginUser(userLoginRq))
    }

    @PostMapping("/register")
    fun register(@RequestBody userCreateRq: UserCreateRq): ResponseEntity<TokenVO> = processServiceExceptions {
        ResponseEntity.ok(authService.createUser(userCreateRq))
    }

    @PostMapping("/refresh")
    fun updateToken(@RequestBody tokenRq: TokenVO): ResponseEntity<TokenVO> = processServiceExceptions {
        ResponseEntity.ok(authService.updateToken(tokenRq.token
                ?: throw BadCredentialsException("Token is empty.")))
    }

}
