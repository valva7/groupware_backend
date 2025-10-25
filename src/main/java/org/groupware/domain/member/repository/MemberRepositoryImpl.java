package org.groupware.domain.member.repository;

import org.groupware.domain.auth.model.entity.RoleEntity;
import org.groupware.domain.auth.repository.JpaRoleRepository;
import org.groupware.global.exception.ErrorCode;
import org.springframework.stereotype.Repository;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.exception.MemberException;

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

        // 권한 설정
        RoleEntity roleEntity = jpaRoleRepository.findByRoleName(member.getInfo().getRole()).orElseThrow(() -> new MemberException(ErrorCode.ALREADY_EXIST));
        memberEntity.setRole(roleEntity);

        return jpaMemberRepository.save(memberEntity);
    }

    public Member findMemberById(Long memberId){
        MemberEntity memberEntity = jpaMemberRepository.findById(memberId).orElseThrow(() -> new MemberException(ErrorCode.NO_EXIST_MEMBER));
        return memberEntity.toMember();
    }

    public Member findMemberByMemberId(String memberId){
        MemberEntity memberEntity = jpaMemberRepository.findByMemberId(memberId);
        return memberEntity == null ? null : memberEntity.toMember();
    }

}
