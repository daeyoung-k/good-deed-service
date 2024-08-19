package com.kane.member.controller

import com.kane.member.service.redis.CacheService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cache")
class CacheController(
    private val cacheService: CacheService
) {
    @PostMapping("/set")
    fun setCache(@RequestBody body: CacheRequest) {
        cacheService.cacheData(body.key, body.value, body.expiration)
    }

    @GetMapping("/get")
    fun getCache(key: String): Any? {
        println( cacheService.getCachedData(key) )

        return "test1"
    }
}

data class CacheRequest(
    val key: String,
    val value: Any,
    val expiration: Long
)
