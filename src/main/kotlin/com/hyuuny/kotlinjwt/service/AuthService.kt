package com.hyuuny.kotlinjwt.service

import com.hyuuny.kotlinjwt.auth.UserDetailsImpl
import com.hyuuny.kotlinjwt.dto.Credentials
import com.hyuuny.kotlinjwt.dto.Token
import com.hyuuny.kotlinjwt.jwt.JwtUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
) {


    fun auth(credentials: Credentials): UserDetailsImpl {
        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken(credentials.username, credentials.password)
        val token = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        return token.principal as UserDetailsImpl
    }

    fun generateToken(username: String): Token {
        val accessToken = jwtUtils.createToken(username)
        return Token(accessToken)
    }

}