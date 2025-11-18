package org.groupware.domain.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.domain.member.repository.JpaMemberRepository;
import org.groupware.global.exception.ErrorCode;
import org.groupware.global.exception.InvalidJwtException;
import org.groupware.global.exception.MemberException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider {

    private static final long TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 30; // 1개월 (30일 기준)

    private static final long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 1440; // 1 day

    private final SecretKey key;
    private final JpaMemberRepository jpaMemberRepository;

    public TokenProvider(@Value("${secret-key}") String secretKey, JpaMemberRepository jpaMemberRepository) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.jpaMemberRepository = jpaMemberRepository;
    }

    /**
     * 액세스 토큰 생성
     * @param member
     * @return
     */
    public String createAccessToken(Member member) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + TOKEN_VALID_TIME);

        // Claims 객체 생성 및 값 설정
        Claims claims = Jwts.claims();
        claims.put("sub", String.valueOf(member.getInfo().getMemberId()));
        claims.put("name", member.getInfo().getMemberName());
        String role = member.getInfo().getRoleName();
        claims.put("role", role);
        claims.put("profileImageUrl", member.getInfo().getProfileImageUrl());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(member.getInfo().getMemberId()))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    /**
     * 액세스 토큰 재발급
     * @param refreshToken
     * @return
     */
    public String createNewAccessToken(String refreshToken) {
        Long userId = getMemberId(refreshToken);
        MemberEntity memberEntity = jpaMemberRepository.findById(userId)
            .orElseThrow(() -> new MemberException(ErrorCode.ALREADY_EXIST));
        return createAccessToken(memberEntity.toMember());
    }

    /**
     * 리프레시 토큰 생성
     * @param member
     * @return
     */
    public String createRefreshToken(Member member) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME);

        // Claims 객체 생성 및 값 설정
        Claims claims = Jwts.claims()
            .setSubject(member.getInfo().getMemberId());

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, key)
            .compact();
    }

    /**
     * 사용자 ID 추출
     * @param token
     * @return
     */
    public Long getMemberId(String token) {
        long id;
        try {
            id = Long.parseLong(
                Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject()
            );
        } catch (NumberFormatException e) {
            log.error("유효하지 않은 토큰: 잘못된 형식의 사용자 ID");
            throw new InvalidJwtException(ErrorCode.JWT_EXCEPTION);
        } catch (JwtException e) {
            log.error("유효하지 않은 토큰: JWT 관련 예외 발생 - {}", e.getMessage());
            throw new InvalidJwtException(ErrorCode.JWT_EXCEPTION);
        }
        return id;
    }

    /**
     * JWT 검증
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token.substring(7)) // Bearer 제거
                .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            log.error("유효하지 않은 토큰: 만료된 토큰");
            throw new InvalidJwtException(ErrorCode.JWT_EXCEPTION);
        } catch (MalformedJwtException e) {
            log.error("유효하지 않은 토큰: 올바르지 않은 형식의 JWT");
            throw new InvalidJwtException(ErrorCode.JWT_EXCEPTION);
        } catch (UnsupportedJwtException e) {
            log.error("유효하지 않은 토큰: 지원되지 않는 JWT 형식");
            throw new InvalidJwtException(ErrorCode.JWT_EXCEPTION);
        } catch (SignatureException e) {
            log.error("유효하지 않은 토큰: 서명이 유효하지 않음");
            throw new InvalidJwtException(ErrorCode.JWT_EXCEPTION);
        } catch (JwtException e) {
            log.error("유효하지 않은 토큰: JWT 관련 예외 발생 - {}", e.getMessage());
            throw new InvalidJwtException(ErrorCode.JWT_EXCEPTION);
        } catch (Exception e) {
            log.error("유효하지 않은 토큰: 기타 예외 발생 - {}", e.getMessage());
            throw new InvalidJwtException(ErrorCode.JWT_EXCEPTION);
        }
    }

}