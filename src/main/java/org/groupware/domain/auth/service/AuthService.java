package org.groupware.domain.auth.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.auth.dto.res.LoginTokenRes;
import org.groupware.domain.auth.model.BaseRole;
import org.groupware.domain.auth.repository.JpaRoleRepository;
import org.groupware.domain.auth.repository.RoleRepository;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.repository.MemberRepository;
import org.groupware.domain.auth.dto.req.LoginReq;
import org.groupware.domain.push.repository.FcmPushRepository;
import org.groupware.global.exception.ErrorCode;
import org.groupware.global.exception.MemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final FcmPushRepository fcmPushRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final RoleRepository roleRepository;


    /**
     * 로그인
     * @param req
     * @return
     */
    public LoginTokenRes login(LoginReq req) {
        // 회원 아이디로 정보 조회
        Member member = memberRepository.findMemberByMemberId(req.memberId());
        if (member == null) {
            throw new MemberException(ErrorCode.WRONG_ACCOUNT);
        }

        // 비밀번호 확인
//        if (!passwordEncoder.matches(req.password(), member.getInfo().getPassword())) {
//            throw new MemberException(ErrorCode.WRONG_ACCOUNT);
//        }

        // Jwt 토큰 발급
        String refreshToken = tokenProvider.createRefreshToken(member);
        String accessToken = tokenProvider.createAccessToken(member);

        // firebase token 저장
        fcmPushRepository.firebaseTokenSave(member, req.fcmToken());

        return new LoginTokenRes(accessToken, refreshToken);
    }

    public List<BaseRole> findRoleList() {
        return roleRepository.findRoleList();
    }

}