package digital.tutors.portal.core.controllers

import com.auth0.jwt.exceptions.TokenExpiredException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class CustomGlobalExceptionHandler : ResponseEntityExceptionHandler() {

    data class CustomError(
            val timestamp: Date,
            val status: Int,
            val message: String?
    )

    @ExceptionHandler(TokenExpiredException::class)
    @Throws(IOException::class)
    fun tokenException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> =
            ResponseEntity(CustomError(Date(), HttpStatus.UNAUTHORIZED.value(), ex.message), HttpStatus.UNAUTHORIZED)

    override fun handleMethodArgumentNotValid(
            ex: MethodArgumentNotValidException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest
    ): ResponseEntity<Any> {

        val body = CustomError(
                Date(),
                status = status.value(),
                message = ex.bindingResult.fieldErrors.joinToString(",") {
                    "${it.field} ${it.defaultMessage}"
                }
        )

        return ResponseEntity(body, headers, status)
    }

}
