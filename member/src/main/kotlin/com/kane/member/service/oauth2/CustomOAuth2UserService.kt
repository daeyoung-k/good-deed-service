package com.kane.member.service.oauth2

import com.kane.member.dto.oauth2.GoogleMapper
import com.kane.member.dto.oauth2.OAuth2Mapper
import com.kane.member.entity.Member
import com.kane.member.entity.Role
import com.kane.member.repository.MemberRepository
import com.kane.member.repository.RoleRepository
import com.kane.member.status.MemberRole
import com.kane.member.utils.JwtUtil
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service


@Service
class CustomOAuth2UserService(
    private val memberRepository: MemberRepository,
    private val roleRepository: RoleRepository,
    private val jwtUtil: JwtUtil
): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User? {

        val delegate = DefaultOAuth2UserService()
        val oAuth2User: OAuth2User = delegate.loadUser(userRequest)

        val provider = userRequest.clientRegistration.registrationId

//        val attributes = oAuth2User.attributes as Map<String, Any>
//        println(attributes)


        val oAuth2Response: OAuth2Mapper = when (provider) {
            "google" -> {
                GoogleMapper(oAuth2User.attributes as Map<String, Any>)
            }

//            "naver" -> {
//                val attributes = oAuth2User.attributes as Map<String, Any>
//                OAuth2UserDto(provider = provider)
//            }
//
//            "kakao" -> {
//                val attributes = oAuth2User.attributes as Map<String, Any>
//                OAuth2UserDto(provider = provider)
//            }

            else -> return null
        }

        val member = getMember(oAuth2Response)

        val oAuthMember = CustomOAuth2User(member)

        return oAuthMember
    }

    private fun getMember(oAuth2Response: OAuth2Mapper): Member {
        val member = memberRepository.findByEmail(oAuth2Response.getEmail()).let {
            if (it == null) {
                val newMember = memberRepository.save(
                        Member(
                            name = oAuth2Response.getName(),
                            email = oAuth2Response.getEmail(),
                            provider = oAuth2Response.getProvider(),
                            providerId = oAuth2Response.getProviderId()
                        )
                    )

                roleRepository.save(
                    Role(
                        role = MemberRole.MEMBER,
                        member = newMember
                    )
                )

                newMember
            } else {
                it
            }
        }
        return member
    }
}