package org.groupware.domain.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonCode {

    private String code;
    private String name;
    private Integer sequence;

}
