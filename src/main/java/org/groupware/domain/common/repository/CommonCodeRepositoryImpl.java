package org.groupware.domain.common.repository;

import java.util.List;
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
        List<CommonCodeEntity> commonCodes = jpaCommonCodeRepository.findByIdGroupCodeAndActiveYn(groupCode, ACTIVE_Y);

        return commonCodes.stream().map(code -> new CommonCode(code.getId().getCode(), code.getCodeName()))
            .toList();
    }

}
