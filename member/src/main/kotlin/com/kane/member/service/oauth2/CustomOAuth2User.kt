package com.kane.member.service.oauth2

import com.kane.member.dto.oauth2.OAuth2UserDto
import com.kane.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2User(
    val member: Member
): OAuth2User {
    override fun getName(): String {
        return member.email
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return OAuth2UserDto(
            name = member.name,
            email = member.email,
            provider = member.provider!!,
            providerId = member.providerId!!
        ) as MutableMap<String, Any>
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        member.roles?.forEach {
            authorities.add(GrantedAuthority { it.role.toString() })
        }
        return authorities
    }
}