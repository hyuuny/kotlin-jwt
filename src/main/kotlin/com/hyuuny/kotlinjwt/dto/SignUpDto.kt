package com.hyuuny.kotlinjwt.dto

import com.hyuuny.kotlinjwt.domain.User

data class SignUpDto(
    val username: String,
    var password: String,
    val name: String,
    var phoneNumber: String? = null,
) {
    fun toEntity() = User(username, password, name, phoneNumber)
}
