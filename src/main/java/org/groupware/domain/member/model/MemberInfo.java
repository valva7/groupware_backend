package org.groupware.domain.member.model;

import lombok.Getter;

@Getter
public class MemberInfo {

    private final String memberId;
    private final String memberName;
    private final String password;
    private final String profileImageUrl;

    public MemberInfo(String memberId, String memberName, String password, String profileImageUrl) {
        if(memberId == null || memberId.isEmpty()){
            throw new IllegalArgumentException();
        }
        if(memberName == null || memberName.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
    }

    public MemberInfo(String memberId, String memberName, String profileImageUrl) {
        if(memberId == null || memberId.isEmpty()){
            throw new IllegalArgumentException();
        }
        if(memberName == null || memberName.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.memberName = memberName;
        this.password = null;
        this.profileImageUrl = profileImageUrl;
    }



}
