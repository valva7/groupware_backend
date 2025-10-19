package org.groupware.domain.member.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import org.groupware.domain.member.model.Member;

public record MemberRes(
    @Schema(description = "직원 ID", type = "String")
    Long memberId,
    @Schema(description = "직원 이름", type = "String")
    String memberName
) {

    public MemberRes(Member member) {
        this(member.getId(), member.getInfo().getMemberName());
    }

}
