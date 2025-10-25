package org.groupware.global.principal;

import org.groupware.domain.auth.model.DetailRole;

public record MemberAuth(
    Long id,
    String role,
    DetailRole detailRole
) {

}