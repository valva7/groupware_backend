package org.groupware.domain.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * CommonCodeEntity의 복합키 (groupCode + code)
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommonCodeId implements Serializable {

    @Column(name = "group_code", nullable = false, length = 100)
    private String groupCode;

    @Column(name = "code", nullable = false, length = 50)
    private String code;
}