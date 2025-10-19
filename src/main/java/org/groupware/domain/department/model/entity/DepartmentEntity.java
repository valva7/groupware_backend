package org.groupware.domain.department.model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "department", indexes = {
    @Index(name = "idx_departmentId", columnList = "code"),
})
public class DepartmentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, unique = true, length = 10)
    private String code; // 부서 코드

    @Column(nullable = false, length = 100)
    private String name; // 부서명

    // 상위 부서 (self-reference)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_department_id")
    private DepartmentEntity parent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "department_id")
    private List<DepartmentMemberEntity> members = new ArrayList<>();

    // 부서장 (MemberEntity FK)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private MemberEntity leader;

    @Column(length = 255)
    private String description; // 부서 설명

}