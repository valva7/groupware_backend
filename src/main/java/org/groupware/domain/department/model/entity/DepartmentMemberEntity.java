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

    @EmbeddedId
    private DepartmentMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id", columnDefinition = "VARCHAR(50)")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("departmentCode")
    @JoinColumn(name = "department_code", columnDefinition = "VARCHAR(50)")
    private DepartmentEntity department;

}
