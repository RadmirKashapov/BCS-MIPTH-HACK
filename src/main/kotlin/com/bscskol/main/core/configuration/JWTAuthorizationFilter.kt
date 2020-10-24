package com.bscskol.main.core.configuration

import com.auth0.jwt.exceptions.TokenExpiredException
import com.bscskol.main.core.auth.JWTDecoder
import com.bscskol.main.core.auth.security.JWTAuthSecurity
import com.bscskol.main.core.configuration.SecurityConstants.Companion.HEADER_STRING
import com.bscskol.main.core.configuration.SecurityConstants.Companion.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class JWTAuthorizationFilter(
        private val jwtDecoder: JWTDecoder,
        authManager: AuthenticationManager?
) : BasicAuthenticationFilter(authManager) {

    @Throws(IOException::class, ServletException::class, TokenExpiredException::class)
    override fun doFilterInternal(req: HttpServletRequest,
                                  res: HttpServletResponse,
                                  chain: FilterChain) {
        val header = req.getHeader(HEADER_STRING)

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res)
            return
        }

        val authentication = try {
            getAuthentication(req)
        } catch (ex: Exception) {
            throw JWTAuthSecurity(ex.message.toString())
        }

        SecurityContextHolder.getContext().authentication = authentication

        chain.doFilter(req, res)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(HEADER_STRING)

        if (token != null) {
            val decodedJWT = jwtDecoder.decode(token)

            val user = decodedJWT.subject
            val authorities = jwtDecoder.getAuthorities(decodedJWT)

            return if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, authorities)
            } else null
        }

        return null
    }

}
