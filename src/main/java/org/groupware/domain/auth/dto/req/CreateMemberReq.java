package org.groupware.domain.auth.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.groupware.global.annotation.ValidBaseRole;
import org.groupware.global.annotation.ValidDepartment;
import org.groupware.global.annotation.ValidDetailRole;
import org.groupware.global.annotation.ValidRank;

public record CreateMemberReq(

// ========================
    // 기본 정보
    // ========================
    @Schema(description = "이름", type = "String")
    @NotEmpty(message = "이름은 필수입니다.")
    @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하여야 합니다.")
    String memberName,

    @Schema(description = "직원 ID", type = "String")
    @NotEmpty(message = "직원 ID는 필수입니다.")
    String memberId,

    @Schema(description = "이메일", type = "String")
    @NotEmpty(message = "이메일은 필수입니다.")
    @Email
    String email,

    @Schema(description = "연락처", type = "String")
    @NotEmpty(message = "연락처는 필수입니다.")
    @Pattern(
        regexp = "^(01[016789])-?(\\d{3,4})-?(\\d{4})$",
        message = "유효한 전화번호 형식이어야 합니다. 예: 010-1234-5678"
    )
    String phone,

    // ========================
    // 직무 정보
    // ========================
    @Schema(description = "부서", type = "String")
    @NotEmpty(message = "부서는 필수입니다.")
    @ValidDepartment
    String department,

    @Schema(description = "직급", type = "String")
    @NotEmpty(message = "직급은 필수입니다.")
    @ValidRank
    String position,

    @Schema(description = "입사일", type = "LocalDate")
    @NotEmpty(message = "입사일은 필수입니다.")
    LocalDate hireDate,

    // ========================
    // 권한 정보
    // ========================
    @Schema(description = "기본 권한", type = "String")
    @NotEmpty(message = "기본 권한은 필수입니다.")
    @ValidBaseRole
    String baseRole,

    @Schema(description = "세부 권한", type = "String")
    @ValidDetailRole
    String detailRole

) {}