package com.kane.member.dto.oauth2

class GoogleMapper(
    private val attributes: Map<String, Any>
): OAuth2Mapper {
    override fun getProvider(): String = "google"
    override fun getProviderId(): String = attributes["sub"].toString()
    override fun getEmail(): String = attributes["email"].toString()
    override fun getName(): String = attributes["name"].toString()
}