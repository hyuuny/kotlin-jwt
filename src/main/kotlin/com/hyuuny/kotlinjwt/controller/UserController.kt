package com.hyuuny.kotlinjwt.controller

import com.hyuuny.kotlinjwt.dto.SignUpDto
import com.hyuuny.kotlinjwt.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/users")
@RestController
class UserController(
    private var userService: UserService,
) {

    @PostMapping("/signup")
    fun signup(@RequestBody signUpDto: SignUpDto): ResponseEntity<String> {
        userService.signup(signUpDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공")
    }

}