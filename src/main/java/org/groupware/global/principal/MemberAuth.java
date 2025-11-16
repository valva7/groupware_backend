package org.groupware.global.principal;

import org.groupware.domain.auth.model.DetailRole;

public record MemberAuth(
    String memberId,
    Long role,
    DetailRole detailRole
) {

}