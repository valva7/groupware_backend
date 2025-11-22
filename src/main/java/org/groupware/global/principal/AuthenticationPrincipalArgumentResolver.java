package org.groupware.global.principal;

import org.groupware.domain.auth.model.CustomMemberDetails;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.repository.MemberRepository;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

// HandlerMethodArgumentResolver -> Spring MVC에서 컨트롤러 메서드의 파라미터를 동적으로 해결하기 위해 사용되는 인터페이스
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberRepository memberRepository;

    public AuthenticationPrincipalArgumentResolver(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            CustomMemberDetails userDetails = (CustomMemberDetails) authentication.getPrincipal();

            Member member = memberRepository.findMemberByMemberId(userDetails.getMember().getInfo().getMemberId());
            // 현재 로그인한 사용자 정보를 반환
            return new MemberAuth(member.getInfo().getMemberId(),
                                member.getInfo().getRoles());
        }

        return null;
    }
}