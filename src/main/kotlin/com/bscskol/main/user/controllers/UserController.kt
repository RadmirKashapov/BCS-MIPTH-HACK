package com.bscskol.main.user.controllers

import com.fasterxml.jackson.annotation.JsonView
import com.bscskol.main.core.auth.AuthorizationService
import com.bscskol.main.core.controllers.BaseController
import com.bscskol.main.core.vo.RestView
import com.bscskol.main.user.services.UserService
import com.bscskol.main.user.vo.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class UserController : BaseController() {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var authorizationService: AuthorizationService

    @GetMapping("/profile")
    fun getProfile(): ResponseEntity<UserDTO> = processServiceExceptions {
        ResponseEntity.ok(userService.getUserByIdOrThrow(authorizationService.currentUserIdOrDie()))
    }

    @PutMapping("/user/{id}")
    fun upsertUser(
            @PathVariable id: String,
            @JsonView(value = [RestView.Request::class])
            @RequestBody userDTO: UserDTO
    ): ResponseEntity<UserDTO> = processServiceExceptions {
        ResponseEntity.ok(userService.upsertUser(id, userDTO))
    }

}
