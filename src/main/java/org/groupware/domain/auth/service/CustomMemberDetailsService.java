package org.groupware.domain.auth.service;

import org.groupware.domain.member.model.Member;
import org.groupware.domain.member.repository.MemberRepository;
import org.groupware.domain.auth.model.CustomMemberDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomMemberDetailsService {

    private final MemberRepository repository;

    public CustomMemberDetailsService(MemberRepository repository) {
        this.repository = repository;
    }

    public UserDetails loadUserByUsername(Member member) throws UsernameNotFoundException {
        return new CustomMemberDetails(member);
    }
}