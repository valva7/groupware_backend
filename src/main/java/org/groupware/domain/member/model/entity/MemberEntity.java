package org.groupware.domain.member.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.groupware.domain.auth.model.entity.RoleEntity;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.MemberInfo;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member", indexes = {
    @Index(name = "idx_memberId", columnList = "memberId"),
})
public class MemberEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(length = 50)
    private String rank; // 직급

    @Column(unique = true, nullable = false, length = 100)
    private String email; // 이메일 (유니크)

    @Column(length = 20)
    private String phone; // 연락처

    @Column(length = 255)
    private String address; // 주소

    @Column(name = "emergency_name", length = 50)
    private String emergencyName; // 비상 연락자 이름

    @Column(name = "emergency_phone", length = 20)
    private String emergencyPhone; // 비상 연락자 연락처

    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;  // 권한

    @Column(name = "project_active_yn", nullable = false)
    private Boolean projectActiveYn; // 프로젝트 관리 권한 여부

    @Column(length = 20, nullable = false)
    private String status; // 재직 상태 (예: ACTIVE, LEAVE, RETIRED)

    @Column(name = "hire_dt", nullable = false)
    private LocalDate hireDt; // 입사일

    @Column(name = "expiration_dt")
    private LocalDate expirationDt; // 퇴사일

    @Column(nullable = false)
    private String password;

    private String profileImageUrl;

    public MemberEntity(Member member) {
        this.id = member.getId();
        this.memberId = member.getInfo().getMemberId();
        this.memberName = member.getInfo().getMemberName();
        this.password = member.getInfo().getPassword();
        this.profileImageUrl = member.getInfo().getProfileImageUrl();
    }

    public Member toMember() {
        return Member.builder()
            .id(this.id)
            .info(
                new MemberInfo(
                    this.memberId
                    , this.memberName
                    , this.email
                    , this.phone
                    , this.address
                    , this.password
                    , this.profileImageUrl
                    , this.emergencyName
                    , this.emergencyPhone
                    , this.rank
                    , role.getRoleName()
                    , this.projectActiveYn
                    , this.status
                    , this.hireDt
                )
            )
            .build();
    }

}