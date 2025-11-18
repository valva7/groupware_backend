package org.groupware.domain.auth.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import org.groupware.domain.auth.model.DetailRole;
import org.groupware.global.annotation.ValidBaseRole;
import org.groupware.global.annotation.ValidDepartment;
import org.groupware.global.annotation.ValidRank;

public record UpdateMemberReq(

    @Schema(description = "직원 ID", type = "String")
    @NotEmpty(message = "직원 ID는 필수입니다.")
    String memberId,

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
    String rank,

    @Schema(description = "재직 상태", type = "String")
    @NotEmpty(message = "재직 상태는 필수입니다.")
    @ValidRank
    String status,

    // ========================
    // 권한 정보
    // ========================
    @Schema(description = "기본 권한", type = "String")
    @NotEmpty(message = "기본 권한은 필수입니다.")
    @ValidBaseRole
    String baseRole,

    @Schema(description = "세부 권한", type = "DetailRole")
    DetailRole detailRole

) {

}
