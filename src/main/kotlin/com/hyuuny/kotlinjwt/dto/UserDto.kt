package com.hyuuny.kotlinjwt.dto

import com.hyuuny.kotlinjwt.domain.User

data class UserDto(
    val username: String,
    var password: String,
) {

    fun toEntity() = User(username, password)

}