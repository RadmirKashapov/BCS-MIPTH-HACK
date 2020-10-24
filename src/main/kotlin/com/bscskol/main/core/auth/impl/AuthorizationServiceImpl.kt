package com.bscskol.main.core.auth.impl

import com.bscskol.main.core.auth.AuthorizationService
import com.bscskol.main.core.auth.JWTDecoder
import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.user.entities.User
import com.bscskol.main.user.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthorizationServiceImpl : AuthorizationService {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var jwtDecoder: JWTDecoder

    @Autowired
    lateinit var userService: UserService

    override fun currentUserIdOrDie(): String =
            currentUserId()
                    .orElseThrow { EntityNotFoundException("No authentication found") }
                    .also { log.debug("Current user id: $it") }

    override fun currentUserOrDie(): User =
            userService.findByIdOrThrow(currentUserIdOrDie())

    override fun currentUserId(): Optional<String> =
            Optional.ofNullable(
                    SecurityContextHolder
                            .getContext()
                            .authentication
                            ?.name
            )

    override fun getUserIdFromToken(token: String?): String? {
        if (token == null) {
            return null
        }

        return jwtDecoder.decode(token).subject
    }

}
