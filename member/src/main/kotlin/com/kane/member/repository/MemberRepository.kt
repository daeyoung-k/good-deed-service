package com.kane.member.repository

import com.kane.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>{

    fun findByEmail(email: String): Member?

    fun findByEmailAndProvider(email: String, provider: String): Member?
}