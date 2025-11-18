package org.groupware.domain.member.model;

import java.time.LocalDate;
import lombok.Getter;
import org.groupware.domain.auth.model.DetailRole;

@Getter
public class MemberInfo {

    // 회원 기본 정보
    private final String memberId;
    private final String memberName;
    private final String email;
    private final String phone;
    private final String address;
    private final String password;
    private final String profileImageUrl;

    private final String emergencyName;
    private final String emergencyPhone;

    // 직무 정보
    private final String rank;

    // 권한
    private final String roleName;
    private final DetailRole detailRole;

    // 재직 상태
    private final String status;

    // 입사일
    private final LocalDate hireDt;


    // 회원 생성
    public MemberInfo(String memberId, String memberName, String email, String phone, String password,
                        String rank, String role, DetailRole detailRole, LocalDate hireDt)
    {

        if(memberId == null || memberId.isEmpty()){
            throw new IllegalArgumentException();
        }
        if(memberName == null || memberName.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
        this.phone = phone;
        this.address = null;
        this.password = password;
        this.profileImageUrl = null;
        this.emergencyName = null;
        this.emergencyPhone = null;
        this.rank = rank;
        this.roleName = role;
        this.detailRole = detailRole;
        this.status = null;
        this.hireDt = hireDt;
    }

    // toMember
    public MemberInfo(String memberId, String memberName, String email, String phone, String address,
                        String password, String profileImageUrl,String emergencyName, String emergencyPhone,
                        String rank, String roleName, DetailRole detailRole, String status, LocalDate hireDt)
    {

        if(memberId == null || memberId.isEmpty()){
            throw new IllegalArgumentException();
        }
        if(memberName == null || memberName.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        this.rank = rank;
        this.roleName = roleName;
        this.detailRole = detailRole;
        this.status = status;
        this.hireDt = hireDt;
    }

}
