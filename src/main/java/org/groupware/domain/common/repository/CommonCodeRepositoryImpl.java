package org.groupware.domain.common.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.groupware.domain.common.model.CommonCode;
import org.groupware.domain.common.model.entity.CommonCodeEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommonCodeRepositoryImpl implements CommonCodeRepository{

    private static final Boolean ACTIVE_Y = true;

    private final JpaCommonCodeRepository jpaCommonCodeRepository;

    public List<CommonCode> findCommonCodeByGroupCode(String groupCode) {
        Optional<List<CommonCodeEntity>> commonCodeEntities = jpaCommonCodeRepository.findByIdGroupCodeAndActiveYn(groupCode, ACTIVE_Y);

        return commonCodeEntities
            .map(list -> list.stream()
            .map(CommonCodeEntity::toCommonCode)
            .toList())
            .orElse(Collections.emptyList());
    }

}
