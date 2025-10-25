package org.groupware.domain.member.service;

import jakarta.transaction.Transactional;
import org.groupware.domain.auth.dto.req.CreateMemberReq;
import org.groupware.domain.department.model.entity.DepartmentEntity;
import org.groupware.domain.department.model.entity.DepartmentMemberEntity;
import org.groupware.domain.department.repository.DepartmentRepository;
import org.groupware.domain.member.dto.req.MemberRes;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.MemberInfo;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.domain.member.repository.JpaMemberRepository;
import org.groupware.domain.member.repository.MemberRepository;
import org.groupware.global.exception.DepartmentException;
import org.groupware.global.exception.MemberException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final String initPasswordPrefix;

    private final MemberRepository memberRepository;
    private final JpaMemberRepository jpaMemberRepository;
    private final DepartmentRepository departmentRepository;

    private final PasswordEncoder passwordEncoder;


    public MemberService(MemberRepository memberRepository, JpaMemberRepository jpaMemberRepository,
                        DepartmentRepository departmentRepository, PasswordEncoder passwordEncoder,
                        @Value("${init-password-prefix}") String initPasswordPrefix)
    {
        this.memberRepository = memberRepository;
        this.jpaMemberRepository = jpaMemberRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
        this.initPasswordPrefix = initPasswordPrefix;
    }

    /**
     * 직원 생성
     * @param req
     */
    @Transactional
    public void createMember(CreateMemberReq req) {
        if (jpaMemberRepository.existsByMemberId(req.memberId())) {
            throw new MemberException("이미 존재하는 아이디입니다.");
        }

        // 직원 정보 INSERT
        String initialPassword = initPasswordPrefix;    // 사용자 초기 비밀번호 : coveone + TODO: 뭘 붙여야 하나?
        String encodedPassword = passwordEncoder.encode(initialPassword);
        MemberInfo memberInfo = new MemberInfo(
            req.memberId(),
            req.memberName(),
            req.email(),
            req.phone(),
            encodedPassword,
            req.rank(),
            req.baseRole(),
            req.detailRole(),
            req.hireDt()
        );
        Member newMember = new Member(null, memberInfo);

        MemberEntity newMemberEntity = memberRepository.saveMember(newMember);

        // TODO: 메뉴 권한 INSERT

        // 직원 부서 INSERT
        DepartmentMemberEntity departmentMemberEntity = new DepartmentMemberEntity();
        departmentMemberEntity.setMember(newMemberEntity);

//        DepartmentEntity departmentEntity = departmentRepository.findByCode(req.department()).orElseThrow(() -> new DepartmentException("존재하지 않는 부서"));
//        departmentEntity.getMembers().add(departmentMemberEntity);

//        departmentRepository.save(departmentEntity);
    }

    /**
     * 직원 정보 조회
     * @param memberId
     * @return
     */
    public MemberRes getMember(String memberId) {
        Member member = memberRepository.findMemberByMemberId(memberId);
        return new MemberRes(member);
    }

}
