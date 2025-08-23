package org.groupware.domain.member.repository;

import org.groupware.domain.auth.model.entity.RoleEntity;
import org.groupware.domain.auth.repository.JpaRoleRepository;
import org.groupware.domain.member.model.entity.MemberRoleEntity;
import org.springframework.stereotype.Repository;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.exception.MemberNotFoundException;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;
    private final JpaRoleRepository jpaRoleRepository;

    public MemberRepositoryImpl(JpaMemberRepository jpaMemberRepository, JpaRoleRepository jpaRoleRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
        this.jpaRoleRepository = jpaRoleRepository;
    }

    public MemberEntity saveMember(Member member){
        MemberEntity memberEntity = new MemberEntity(member);

        // 권한 부여
        member.getInfo().getRoles().forEach(role -> {
            RoleEntity roleEntity = jpaRoleRepository.findByRoleName(role).orElseThrow(() -> new MemberNotFoundException("존재하지 않는 권한"));

            MemberRoleEntity memberRole = new MemberRoleEntity();
            memberRole.setMember(memberEntity);
            memberRole.setRole(roleEntity);

            memberEntity.getMemberRoles().add(memberRole);
        });

        return jpaMemberRepository.save(memberEntity);
    }

    public Member findMemberById(Long memberId){
        MemberEntity memberEntity = jpaMemberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("존재하지 않는 사용자"));
        return memberEntity.toMember();
    }

    public Member findMemberByMemberId(String memberId){
        MemberEntity memberEntity = jpaMemberRepository.findByMemberId(memberId);
        return memberEntity == null ? null : memberEntity.toMember();
    }

}
