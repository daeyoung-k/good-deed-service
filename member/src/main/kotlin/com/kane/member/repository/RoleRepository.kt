package com.kane.member.repository

import com.kane.member.entity.Role
import com.kane.member.status.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByRole(role: MemberRole): Role?
}