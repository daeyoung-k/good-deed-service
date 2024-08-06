package com.kane.member.repository

import com.kane.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRoleRepository: JpaRepository<MemberRole, Long> {

}