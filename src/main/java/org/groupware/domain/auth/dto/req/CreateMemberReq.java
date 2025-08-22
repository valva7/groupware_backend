package org.groupware.domain.auth.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateMemberReq(

    @Schema(description = "아이디", type = "String")
    @NotEmpty(message = "아이디은 필수입니다.")
    String memberId,

    @Schema(description = "이름", type = "String")
    @NotEmpty(message = "이름은 필수입니다.")
    @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하여야 합니다.")
    String memberName,

    @Schema(description = "생년월일", type = "String")
    @NotEmpty(message = "생년월일은 필수입니다.")
    @Pattern(regexp = "\\d{6}", message = "생년월일은 숫자 6자리여야 합니다.")
    String birthday

) {}