package com.hyuuny.kotlinjwt.dto

import com.hyuuny.kotlinjwt.auth.UserDetailsImpl

data class Credentials(
    val username: String,
    val password: String,
)

data class Token(
    val accessToken: String,
)

data class UserWithToken(
    val userInfo: UserDetailsImpl,
    val token: Token,
)