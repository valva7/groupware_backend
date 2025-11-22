package org.groupware.domain.auth.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.groupware.domain.auth.model.BaseRole;

public record BaseRoleRes(
    @Schema(description = "Role List", type = "List")
    List<BaseRole> roles
) {

}
