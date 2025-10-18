package org.groupware.domain.member.service;

import java.util.List;
import org.groupware.domain.auth.dto.req.CreateMemberReq;
import org.groupware.domain.member.dto.req.MemberRes;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.MemberInfo;
import org.groupware.domain.member.repository.JpaMemberRepository;
import org.groupware.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final String initPasswordPrefix;

    private final MemberRepository memberRepository;
    private final JpaMemberRepository jpaMemberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, JpaMemberRepository jpaMemberRepository,
                        PasswordEncoder passwordEncoder, @Value("${init-password-prefix}") String initPasswordPrefix) {
        this.memberRepository = memberRepository;
        this.jpaMemberRepository = jpaMemberRepository;
        this.passwordEncoder = passwordEncoder;
        this.initPasswordPrefix = initPasswordPrefix;
    }

    /**
     * 사용자 생성
     * @param req
     */
    public void createMember(CreateMemberReq req) {
        if (jpaMemberRepository.existsByMemberId(req.memberId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 회원 정보 INSERT
        // 사용자 초기 비밀번호 : coveone + TODO: 뭘 붙여야 하나?
        String initialPassword = initPasswordPrefix;
        String encodedPassword = passwordEncoder.encode(initialPassword);
        MemberInfo memberInfo = new MemberInfo(req.memberId(), req.memberName(), encodedPassword, List.of(req.baseRole()),null);
        Member newMember = new Member(null, memberInfo);

        memberRepository.saveMember(newMember);

        // 회원 권한 INSERT

        // 회원 부서 INSERT
    }

    /**
     * 멤버 정보 조회
     * @param memberId
     * @return
     */
    public MemberRes getMember(String memberId) {
        Member member = memberRepository.findMemberByMemberId(memberId);
        return new MemberRes(member);
    }

}
