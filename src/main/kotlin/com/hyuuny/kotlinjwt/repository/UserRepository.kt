package com.hyuuny.kotlinjwt.repository

import com.hyuuny.kotlinjwt.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?

}