package com.hyuuny.kotlinjwt.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hyuuny.kotlinjwt.common.IntegrationTest
import com.hyuuny.kotlinjwt.dto.Credentials
import com.hyuuny.kotlinjwt.dto.SignUpDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post


class AuthControllerTest : IntegrationTest() {

    @AfterEach
    fun teamDown() {
        userRepository.deleteAll()
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