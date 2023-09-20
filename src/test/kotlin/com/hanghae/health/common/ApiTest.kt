package com.hanghae.health.common

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class ApiTest {

    @LocalServerPort private val port = 0

    @Autowired private val databaseCleaner: DatabaseCleaner? = null

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        RestAssured.port = port
        databaseCleaner?.afterPropertiesSet()
        databaseCleaner?.execute()
    }
}
