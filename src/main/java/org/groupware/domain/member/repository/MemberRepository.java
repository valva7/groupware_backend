package org.groupware.domain.member.repository;

import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.entity.MemberEntity;

public interface MemberRepository {

    MemberEntity saveMember(Member member);

    Member findMemberById(Long memberId);

    Member findMemberByMemberId(String email);
}
