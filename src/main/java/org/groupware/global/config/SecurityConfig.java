package org.groupware.global.config;


import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.auth.service.CustomMemberDetailsService;
import org.groupware.domain.auth.service.TokenProvider;
import org.groupware.domain.member.repository.MemberRepository;
import org.groupware.global.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] ALL_ALLOWLIST = {
        "/swagger-ui/**",
        "/v3/**",
        "/login/**",
        "/images/**",
        "/auth/**",
        "/actuator/prometheus", // 인증 추가 예정
        "/actuator/**", // 인증 추가 예정
        "/member/create"
    };

    private static final String[] ADMIN_ALLOWLIST = {
//        "/member/create"
    };

    private final MemberRepository repository;

    private final CustomMemberDetailsService customMemberDetailsService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final TokenProvider tokenProvider;

    public SecurityConfig(
        MemberRepository repository,
        CustomMemberDetailsService customMemberDetailsService,
        CustomAuthenticationEntryPoint authenticationEntryPoint,
        CustomAccessDeniedHandler accessDeniedHandler,
        TokenProvider tokenProvider
    ) {
        this.repository = repository;
        this.customMemberDetailsService = customMemberDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{
        // CSRF 비활성화
        http.csrf(AbstractHttpConfigurer::disable);

        // CORS 설정
        http.cors(Customizer.withDefaults());

        // AUTH_ALLOWLIST에 포함된 경로는 인증 없이 허용
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(ALL_ALLOWLIST).permitAll() // 인증 없이 접근 가능한 URI
            .requestMatchers(ADMIN_ALLOWLIST).hasRole("ADMIN")
            .anyRequest().authenticated()); // 나머지 경로는 인증 필요

        // JWT 인증 필터 추가 (requestMatchers로 설정한 경로 외에서 동작하도록)
        http.addFilterBefore(new JwtAuthFilter(customMemberDetailsService, tokenProvider, repository), UsernamePasswordAuthenticationFilter.class);

        // 예외 처리
        http.exceptionHandling(handling -> handling
            .authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedHandler(accessDeniedHandler));

        // 세션 관리 (stateless)
        http.sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Spring Security 기본 로그인 화면 비활성화
        http.formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(ALL_ALLOWLIST);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
