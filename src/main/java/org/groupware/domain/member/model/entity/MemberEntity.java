package org.groupware.domain.member.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
            .info(new MemberInfo(this.memberId, this.memberName, this.password, this.profileImageUrl))
            .build();
    }

}