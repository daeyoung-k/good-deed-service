package com.kane.member.entity

import jakarta.persistence.*

@Entity
@Table(name = "member_role")
data class MemberRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member,

    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: Role
)
