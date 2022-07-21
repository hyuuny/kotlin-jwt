package com.hyuuny.kotlinjwt.service

import com.hyuuny.kotlinjwt.auth.UserDetailsImpl
import com.hyuuny.kotlinjwt.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private var userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val loadedUser = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("존재하지 않는 회원입니다.")
        return UserDetailsImpl(loadedUser)
    }

}