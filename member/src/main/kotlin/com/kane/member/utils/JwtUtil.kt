package com.kane.member.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

const val ACCESS_EXPIRATION: Long = 1000 * 60 * 10
const val REFRESH_EXPIRATION: Long = 1000 * 60 * 60 * 24

@Component
class JwtUtil(
    @Value("\${jwt.secret}")
    private val secretKey: String
) {

    fun createToken(email: String, role: List<String>): String {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())
        val now = Date()
        val accessExpiration = Date(now.time + ACCESS_EXPIRATION)

        return Jwts.builder()
            .subject(email)
            .claim("type", "ACCESS")
            .claim("role", role)
            .issuedAt(now)
            .expiration(accessExpiration)
            .signWith(key)
            .compact()
    }
}