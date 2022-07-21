package com.hyuuny.kotlinjwt.domain

import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Entity

@Entity
class User(
    username: String,
    password: String,
    auth: String = "ROLE_USER",
) : BaseEntity() {

    var username = username
        private set

    var password: String = password
        private set

    var auth = auth
        private set

    val authorities
        get() = if (this.auth.isNotEmpty()) this.auth.split(",").toString() else ""

    fun cipher(passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(this.password)
    }

    fun assignRole() {
        this.auth = "ROLE_USER"
    }
}