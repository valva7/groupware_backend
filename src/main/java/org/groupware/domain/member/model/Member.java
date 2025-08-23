package org.groupware.domain.member.model;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private transient MemberInfo info;

}
