package com.hyuuny.kotlinjwt.domain

import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Entity

@Entity
class User(
    username: String,
    password: String,
    name: String,
    phoneNumber: String?,
    auth: String = "ROLE_USER",
) : BaseEntity() {

    var username = username
        private set

    var password = password
        private set

    var name = name
        private set

    var phoneNumber = phoneNumber
        private set

    var auth = auth
        private set

    val authorities
        get() = if (this.auth.isNotEmpty()) this.auth.split(",").toString() else ""

    fun cipher(passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(this.password)
    }

}