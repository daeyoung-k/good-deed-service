package com.kane.member.entity

import com.kane.common.entity.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "member")
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @Column(length = 30)
    val nickName: String? = null,

    @Column(length = 50)
    val email: String,

    val birthday: LocalDateTime? = null,

    @Column(length = 20)
    val tel: String? = null,

    @Column(length = 10)
    val provider: String? = null,

    val providerId: String? = null,

    val password: String? = null,

): BaseTimeEntity() {

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val roles: List<Role>? = null
}
