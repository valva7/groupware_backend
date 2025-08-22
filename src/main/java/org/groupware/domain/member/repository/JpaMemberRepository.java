package org.groupware.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.groupware.domain.member.model.entity.MemberEntity;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {


    MemberEntity findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);


}
