package com.bscskol.main.core.configuration

class SecurityConstants {

    companion object {

        const val HEADER_STRING = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
        const val EXPIRATION_TIME = 864_000_000 // 10 days
        const val TOKEN_ISSUER = "tutors.portal"

    }

}
