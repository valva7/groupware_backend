package org.groupware.domain.member.model;

import java.util.List;
import lombok.Getter;

@Getter
public class MemberInfo {

    private final String memberId;
    private final String memberName;
    private final String password;
    private final List<String> roles;
    private final String profileImageUrl;

    public MemberInfo(String memberId, String memberName, String password, List<String> roles, String profileImageUrl) {
        if(memberId == null || memberId.isEmpty()){
            throw new IllegalArgumentException();
        }
        if(memberName == null || memberName.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.roles = roles;
        this.profileImageUrl = profileImageUrl;
    }

}
