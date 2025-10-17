package org.groupware.domain.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.auth.dto.res.LoginTokenRes;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.repository.MemberRepository;
import org.groupware.domain.auth.dto.req.LoginReq;
import org.groupware.domain.push.repository.FcmPushRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final TokenProvider tokenProvider;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final FcmPushRepository fcmPushRepository;

    public AuthService(TokenProvider tokenProvider, MemberRepository memberRepository,PasswordEncoder passwordEncoder, FcmPushRepository fcmPushRepository) {
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.fcmPushRepository = fcmPushRepository;
    }


    /**
     * 로그인
     * @param req
     * @return
     */
    public LoginTokenRes login(LoginReq req) {
        // 이메일로 회원 정보 조회
        Member member = memberRepository.findMemberByMemberId(req.memberId());
        if (member == null) {
            throw new IllegalArgumentException("로그인 정보가 틀렸습니다.");
        }
        // 비밀번호 확인
        if (!passwordEncoder.matches(req.password(), member.getInfo().getPassword())) {
            throw new IllegalArgumentException("로그인 정보가 틀렸습니다.");
        }

        // Jwt 토큰 발급
        String refreshToken = tokenProvider.createRefreshToken(member);
        String accessToken = tokenProvider.createAccessToken(member);

        // firebase token 저장
        fcmPushRepository.firebaseTokenSave(member, req.fcmToken());

        return new LoginTokenRes(accessToken, refreshToken);
    }

}