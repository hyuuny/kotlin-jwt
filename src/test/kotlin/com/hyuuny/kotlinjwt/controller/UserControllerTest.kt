package com.hyuuny.kotlinjwt.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hyuuny.kotlinjwt.common.IntegrationTest
import com.hyuuny.kotlinjwt.dto.Credentials
import com.hyuuny.kotlinjwt.dto.SignUpDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class UserControllerTest : IntegrationTest() {

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
    fun `회원정보 조회 - 인증된 사용자여야한다`() {
        userService.signup(SignUpDto("hyuuny", "1111", "헬로", "01012341234").toEntity())
        val credentials = Credentials("hyuuny", "1111")
        val auth = authService.auth(credentials)
        val token = authService.generateToken(credentials.username)

        mockMvc.get("/api/v1/users/info") {
            header("Authorization", "Bearer ${token.accessToken}")
        }.andExpect {
            status { isOk() }
        }.andDo {
            print()
        }
    }

    @Test
    fun `회원정보 조회 - 토큰 X 접근 불가`() {
        mockMvc.get("/api/v1/users/info") {
        }.andExpect {
            status { is4xxClientError() }
        }.andDo {
            print()
        }
    }

}