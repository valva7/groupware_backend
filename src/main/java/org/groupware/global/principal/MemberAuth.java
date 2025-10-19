package org.groupware.global.principal;

public record MemberAuth(
    Long id,
    String role,
    Boolean projectActiveYn
) {

}