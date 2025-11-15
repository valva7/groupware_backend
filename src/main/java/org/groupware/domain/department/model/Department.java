package org.groupware.domain.department.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private Long id;
    private String code;
    private String name;
    private String parentCode;
    private String description;

}
