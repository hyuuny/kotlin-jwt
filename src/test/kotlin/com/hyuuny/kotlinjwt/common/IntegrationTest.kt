package com.hyuuny.kotlinjwt.common

import com.hyuuny.kotlinjwt.repository.UserRepository
import com.hyuuny.kotlinjwt.service.AuthService
import com.hyuuny.kotlinjwt.service.UserService
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = [
        "spring.config.location="
                + "classpath:application.yml,"
                + "classpath:application-test.yml"]
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class IntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc


    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var authService: AuthService

}