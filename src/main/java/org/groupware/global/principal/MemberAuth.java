package org.groupware.global.principal;

import java.util.List;

public record MemberAuth(
    String memberId,
    List<String> roles
) {

}