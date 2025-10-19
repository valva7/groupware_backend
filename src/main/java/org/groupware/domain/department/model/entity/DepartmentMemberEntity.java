package org.groupware.domain.department.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "department_member")
public class DepartmentMemberEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

}
