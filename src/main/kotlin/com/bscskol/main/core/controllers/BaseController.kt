package com.bscskol.main.core.controllers

import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.errors.TokenIDException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.server.ResponseStatusException

open class BaseController {

    private val log = LoggerFactory.getLogger(BaseController::class.java)

    protected fun <T> processServiceExceptions(block: () -> T) =
            try {
                block()
            } catch (e: BadCredentialsException) {
                log.error("$e")
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad credentials", e)
            } catch (e: EntityNotFoundException) {
                log.error("$e")
                throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message, e)
            } catch (e: TokenIDException) {
                log.error("$e")
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message, e)
            } catch (e: Exception) {
                log.error("$e")
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Error occurred processing the request", e)
            }

}
