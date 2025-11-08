package org.groupware.domain.auth.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginReq(

    @Schema(description = "사용자 아이디", type = "String")
    @NotEmpty(message = "아이디는 필수입니다.")
    String memberId,

    @Schema(description = "비밀번호", type = "String")
    @NotEmpty(message = "비밀번호는 필수입니다.")
    String password,

    @Schema(description = "Fcm Push Token", type = "String")
    String fcmToken

) {

}
