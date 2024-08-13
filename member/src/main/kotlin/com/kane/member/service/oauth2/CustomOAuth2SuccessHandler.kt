package com.kane.member.service.oauth2

import com.kane.member.utils.JwtUtil
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component


@Component
class CustomOAuth2SuccessHandler(
    private val jwtUtil: JwtUtil
): SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oauth2User = authentication.principal as CustomOAuth2User

        val token = jwtUtil.createToken(oauth2User.name, oauth2User.authorities.map { it.authority })
        val cookie = createCookie("Authorization", token)

        response.addCookie(cookie)
        response.sendRedirect("http://localhost:3000")
    }

    private fun createCookie(
        key: String,
        value: String,
        ): Cookie {
        return Cookie(key, value).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 * 24 * 7
        }
    }

}