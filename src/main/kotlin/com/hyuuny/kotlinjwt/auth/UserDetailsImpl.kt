package com.hyuuny.kotlinjwt.auth

import com.hyuuny.kotlinjwt.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val user: User) : UserDetails {

    private val enabled = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(user.authorities)
    }

    override fun getPassword() = user.password

    override fun getUsername() = user.username

    override fun isAccountNonExpired() = this.enabled

    override fun isAccountNonLocked() = this.enabled

    override fun isCredentialsNonExpired() = this.enabled

    override fun isEnabled() = this.enabled
}