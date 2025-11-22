package org.groupware.domain.member.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.groupware.domain.auth.dto.req.UpdateMemberReq;
import org.groupware.domain.auth.model.entity.RolesEntity;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.MemberInfo;
import org.groupware.global.enums.MemberStatus;
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
    @Column(nullable = false, unique = true)
    private String memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(length = 50)
    private String rankCd; // 직급

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "member_roles",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RolesEntity> roles = new ArrayList<>();

    @Column(length = 20, nullable = false)
    private String status; // 재직 상태

    @Column(name = "hire_dt", nullable = false)
    private LocalDate hireDt; // 입사일

    @Column(name = "expiration_dt")
    private LocalDate expirationDt; // 퇴사일

    @Column(nullable = false)
    private String password;

    private String profileImageUrl;

    public MemberEntity(Member member) {
        this.memberId = member.getInfo().getMemberId();
        this.memberName = member.getInfo().getMemberName();
        this.rankCd = member.getInfo().getRank();
        this.email = member.getInfo().getEmail();
        this.phone = member.getInfo().getPhone();
        this.status = MemberStatus.WORK.getCode();
        this.hireDt = member.getInfo().getHireDt();
        this.password = member.getInfo().getPassword();
        this.profileImageUrl = member.getInfo().getProfileImageUrl();
    }

    public Member toMember() {
        List<String> roles = this.roles.stream()
            .map(RolesEntity::getRoleId)
            .toList();

        return Member.builder()
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
                    , this.rankCd
                    , roles
                    , this.status
                    , this.hireDt
                )
            )
            .build();
    }

    public void update(UpdateMemberReq req) {
        this.rankCd = req.rank();
        this.status = req.status();
    }

}