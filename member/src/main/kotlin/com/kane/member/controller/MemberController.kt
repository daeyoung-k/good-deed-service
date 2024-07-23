package com.kane.member.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController {

    @GetMapping("/member")
    fun getMemberList(
        @RequestHeader headers: Map<String, String>,
        request: HttpServletRequest
    ): List<String> {
        return listOf("member1", "member2", "member3")
    }
}