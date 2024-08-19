package com.kane.member.service.redis

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CacheServiceTest(
    private val cacheService: CacheService
) {

    @Test
    fun cacheData() {
        cacheService.cacheData("test", "test", 1L)
    }

    @Test
    fun getCachedData() {
        cacheService.getCachedData("test")
    }
}