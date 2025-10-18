package org.groupware.domain.department.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "department")
public class DepartmentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 상위 부서 (self-reference)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_department_id")
    private DepartmentEntity parent;

    // 부서장 (MemberEntity FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity leader;

    @Column(nullable = false, length = 100)
    private String name; // 부서명

    @Column(length = 255)
    private String description; // 부서 설명

    @Column(name = "department_tag", length = 50, unique = true, nullable = false)
    private String departmentTag; // 부서 코드/태그
}