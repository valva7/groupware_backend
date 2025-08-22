package org.groupware.domain.member.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import org.groupware.domain.member.model.Member;

public record MemberRes(
    @Schema(description = "멤버 아이디", type = "String")
    Long memberId,
    @Schema(description = "멤버 이름", type = "String")
    String memberName
) {

    public MemberRes(Member member) {
        this(member.getId(), member.getInfo().getMemberName());
    }

}
