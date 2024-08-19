package com.kane.member.service.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class CacheService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun cacheData(key: String, value: Any, expiration: Long) {
        redisTemplate.opsForValue().set(key, value, expiration, TimeUnit.SECONDS)
    }

    fun getCachedData(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }
}