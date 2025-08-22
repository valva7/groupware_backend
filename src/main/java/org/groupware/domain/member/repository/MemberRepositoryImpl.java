package org.groupware.domain.member.repository;

import org.springframework.stereotype.Repository;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.exception.MemberNotFoundException;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    public MemberRepositoryImpl(JpaMemberRepository jpaMemberRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
    }

    public MemberEntity saveMember(Member member){
        MemberEntity memberEntity = new MemberEntity(member);
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
