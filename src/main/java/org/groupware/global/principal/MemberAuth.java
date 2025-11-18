package org.groupware.global.principal;

import org.groupware.domain.auth.model.DetailRole;

public record MemberAuth(
    String memberId,
    String role,
    DetailRole detailRole
) {

}