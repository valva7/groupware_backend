package org.groupware.domain.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseRole {

    private String roleName;
    private String description;

}
