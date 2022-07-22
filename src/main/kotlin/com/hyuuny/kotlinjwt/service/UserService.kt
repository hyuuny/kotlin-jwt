package com.hyuuny.kotlinjwt.service

import com.hyuuny.kotlinjwt.domain.User
import com.hyuuny.kotlinjwt.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserService(
    private var userRepository: UserRepository,
    private var passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun signup(user: User) {
        user.cipher(passwordEncoder)
        userRepository.save(user)
    }

}