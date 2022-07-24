package com.hyuuny.kotlinjwt.jwt

import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(private val jwtUtils: JwtUtils) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader =
            request.getHeader(HttpHeaders.AUTHORIZATION) ?: return filterChain.doFilter(
                request,
                response
            )
        val token = authHeader.replace("Bearer ", "");

        // 검증
        if (jwtUtils.validation(token)) {
            val username = jwtUtils.parseUsername(token)
            val authentication = jwtUtils.getAuthentication(username)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

}