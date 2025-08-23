package org.groupware.global.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.groupware.domain.auth.model.CustomMemberDetails;
import org.groupware.domain.auth.service.CustomMemberDetailsService;
import org.groupware.domain.auth.service.TokenProvider;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.repository.MemberRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

// OncePerRequestFilter -> 매 요청마다 한 번만 실행되는 필터
public class JwtAuthFilter extends OncePerRequestFilter {

    private final CustomMemberDetailsService customMemberDetailsService;
    private final TokenProvider tokenProvider;

    private final MemberRepository repository;

    public JwtAuthFilter(
        CustomMemberDetailsService customMemberDetailsService,
        TokenProvider tokenProvider,
        MemberRepository repository
    ) {
        this.customMemberDetailsService = customMemberDetailsService;
        this.tokenProvider = tokenProvider;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청 헤더의 토큰 Get
        String authorizationHeader = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            String accessToken = authorizationHeader.substring(7);
            // 토큰으로 멤버 인증
            Long id = tokenProvider.getMemberId(accessToken);
            Member member = repository.findMemberById(id);

            // 존재할 경우 유저 정보를 SecurityContextHolder에 저장
            if(member != null){
                // 커스텀한 멤버 Object로 저장
                CustomMemberDetails userDetails = (CustomMemberDetails) customMemberDetailsService.loadUserByUsername(member);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
