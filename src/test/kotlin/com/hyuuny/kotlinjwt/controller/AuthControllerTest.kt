package com.hyuuny.kotlinjwt.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hyuuny.kotlinjwt.common.IntegrationTest
import com.hyuuny.kotlinjwt.dto.Credentials
import com.hyuuny.kotlinjwt.dto.SignUpDto
import com.hyuuny.kotlinjwt.repository.UserRepository
import com.hyuuny.kotlinjwt.service.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post


class AuthControllerTest : IntegrationTest() {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @AfterEach
    fun teamDown() {
        userRepository.deleteAll()
    }

    @Test
    fun `회원가입 성공`() {
        val signUpDto = SignUpDto("hello", "1234", "헬로", "01012341234")

        mockMvc.post("/api/v1/users/signup") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(signUpDto)
        }.andExpect {
            status { isCreated() }
        }.andDo {
            print()
        }
    }

    @Test
    fun `로그인 성공`() {
        userService.signup(SignUpDto("hyuuny", "1111", "헬로", "01012341234").toEntity())
        val credentials = Credentials("hyuuny", "1111")

        mockMvc.post("/api/v1/auth") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(credentials)
        }.andExpect {
            status { isOk() }
        }.andDo {
            print()
        }
    }

}