package org.groupware.domain.common.repository;

import java.util.List;
import java.util.Optional;
import org.groupware.domain.common.model.entity.CommonCodeEntity;
import org.groupware.domain.common.model.entity.CommonCodeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommonCodeRepository extends JpaRepository<CommonCodeEntity, CommonCodeId> {

    boolean existsByIdGroupCodeAndIdCode(String groupCode, String code);

    Optional<List<CommonCodeEntity>> findByIdGroupCodeAndActiveYn(String groupCode, Boolean activeYn);

}
