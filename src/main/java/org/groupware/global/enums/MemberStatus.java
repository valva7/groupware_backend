package org.groupware.global.enums;

import java.util.Arrays;

public enum MemberStatus {
    WORK("WORK", "재직중"),
    METLV("METLV", "출산휴가"),
    RET("RET", "퇴사");

    private final String code;
    private final String description;

    MemberStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MemberStatus fromCode(String code) {
        return Arrays.stream(values())
            .filter(r -> r.code.equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown code: " + code));
    }
}
