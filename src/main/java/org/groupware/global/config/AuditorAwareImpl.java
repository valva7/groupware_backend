package org.groupware.global.config;

import java.util.Optional;
import org.groupware.domain.auth.model.CustomMemberDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Spring Security 로그인 사용자 이름 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        CustomMemberDetails userDetails = (CustomMemberDetails) authentication.getPrincipal();
        return Optional.of(userDetails.getMember().getInfo().getMemberId());
    }
}
