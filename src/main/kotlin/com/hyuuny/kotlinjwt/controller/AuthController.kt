package com.hyuuny.kotlinjwt.controller

import com.hyuuny.kotlinjwt.dto.Credentials
import com.hyuuny.kotlinjwt.dto.UserWithToken
import com.hyuuny.kotlinjwt.service.AuthService
import com.hyuuny.kotlinjwt.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class AuthController(
    private var authService: AuthService,
) {

    @PostMapping("/auth")
    fun auth(@RequestBody credentials: Credentials): UserWithToken {
        val authedUser = authService.auth(credentials)
        val token = authService.generateToken(authedUser.username)
        return UserWithToken(authedUser, token)
    }

}