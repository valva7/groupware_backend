package org.groupware.domain.common.model.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import org.groupware.domain.common.model.CommonCode;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "common_code")
public class CommonCodeEntity extends TimeBaseEntity {

    @EmbeddedId
    private CommonCodeId id; // ✅ 복합키 (groupCode + code)

    @Column(name = "code_name", nullable = false, length = 100)
    private String codeName; // 코드 이름

    @Column(length = 255)
    private String description; // 설명

    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn; // 사용 여부

    @Column(nullable = false)
    private Integer sequence; // 노출 순서

    public CommonCode toCommonCode(){
        return CommonCode.builder()
            .code(this.id.getCode())
            .name(this.codeName)
            .sequence(this.sequence)
            .build();
    }
}
