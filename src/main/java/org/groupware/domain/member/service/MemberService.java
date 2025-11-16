package org.groupware.domain.member.service;

import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;
import org.groupware.domain.auth.dto.req.CreateMemberReq;
import org.groupware.domain.auth.dto.req.UpdateMemberReq;
import org.groupware.domain.auth.model.entity.RoleEntity;
import org.groupware.domain.auth.repository.JpaRoleRepository;
import org.groupware.domain.department.model.entity.DepartmentMemberEntity;
import org.groupware.domain.department.model.entity.DepartmentMemberId;
import org.groupware.domain.department.repository.DepartmentRepository;
import org.groupware.domain.department.repository.JpaDepartmentMemberRepository;
import org.groupware.domain.member.dto.req.MemberRes;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.MemberInfo;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.domain.member.repository.JpaMemberRepository;
import org.groupware.domain.member.repository.MemberRepository;
import org.groupware.global.exception.ErrorCode;
import org.groupware.global.exception.MemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JpaMemberRepository jpaMemberRepository;
    private final DepartmentRepository departmentRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaDepartmentMemberRepository jpaDepartmentMemberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 직원 정보 조회
     *
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true)
    public MemberRes getMember(String memberId) {
        Member member = memberRepository.findMemberByMemberId(memberId);
        return new MemberRes(member);
    }

    /**
     * 직원 생성
     *
     * @param req
     */
    @Transactional(readOnly = false)
    public void saveMember(CreateMemberReq req) {
        // 직원 ID 중복체크
        if (jpaMemberRepository.existsByMemberId(req.memberId())) {
            throw new MemberException(ErrorCode.ALREADY_EXIST);
        }

        // 초기 비밀번호 생성
        String generatedPassword = generateRandomPassword();
        String encodedPassword = passwordEncoder.encode(generatedPassword);

        // 직원 정보 생성
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

        Member newMember = new Member(memberInfo);
        MemberEntity newMemberEntity = memberRepository.saveMember(newMember);

        // TODO: 메뉴 권한 INSERT

        // 직원 부서 INSERT
        DepartmentMemberEntity departmentMemberEntity = new DepartmentMemberEntity();
        DepartmentMemberId departmentMemberId = new DepartmentMemberId(newMemberEntity.getMemberId(), req.department());
        departmentMemberEntity.setId(departmentMemberId);

        jpaDepartmentMemberRepository.save(departmentMemberEntity);

        // TODO: ID 및 Password를 email로 전달
    }

    public String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(8);

        String charSet =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789" +
            "!@#$%^&*";

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(charSet.length());
            sb.append(charSet.charAt(index));
        }

        return sb.toString();
    }

    /**
     * 직원 수정
     *
     * @param req
     */
    @Transactional(readOnly = false)
    public void updateMember(UpdateMemberReq req) {
        // 회원 정보 수정
        jpaMemberRepository.findByMemberId(req.memberId())
            .ifPresentOrElse(
                memberEntity -> {
                        RoleEntity roleEntity = jpaRoleRepository.findById(req.baseRole()).orElseThrow(() -> new MemberException(ErrorCode.NO_EXIST_ROLE));
                        memberEntity.setRole(roleEntity);
                        memberEntity.update(req);
                    },
                () -> {throw new MemberException(ErrorCode.NO_EXIST_MEMBER);}
            );

        // 회원 기존 부서 정보 삭제 (현재 구조는 부서(n) : 직원(n) 구조이기에 기존 부서 정보는 삭제), 일단은 1:1 구조로 보고 진행.
        jpaDepartmentMemberRepository.findById(new DepartmentMemberId(req.memberId(), req.department()))
            .ifPresentOrElse(
                departmentMemberEntity -> {
                    if (!departmentMemberEntity.getDepartment().getCode().equals(req.department())) {
                        // 기존 부서 회원 삭제
                        jpaDepartmentMemberRepository.delete(departmentMemberEntity);

                        // 회원 부서 정보 추가
                        DepartmentMemberEntity newDepartmentMemberEntity = new DepartmentMemberEntity();
                        DepartmentMemberId departmentMemberId = new DepartmentMemberId(req.memberId(), req.department());
                        newDepartmentMemberEntity.setId(departmentMemberId);
                        jpaDepartmentMemberRepository.save(newDepartmentMemberEntity);
                    }
                },
                () -> {throw new MemberException(ErrorCode.NO_EXIST_MEMBER);}
            );



    }


}
