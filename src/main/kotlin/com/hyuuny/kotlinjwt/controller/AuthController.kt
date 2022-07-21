package com.hyuuny.kotlinjwt.controller

import com.hyuuny.kotlinjwt.dto.UserDto
import com.hyuuny.kotlinjwt.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class AuthController(
    private var userService: UserService,
) {

    @PostMapping("/signup")
    fun signup(@RequestBody userDto: UserDto): String {
        userService.signup(userDto.toEntity())
        return "회원가입 완료"
    }

}