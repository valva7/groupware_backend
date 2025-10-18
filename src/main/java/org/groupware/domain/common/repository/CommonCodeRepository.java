package org.groupware.domain.common.repository;

import org.groupware.domain.common.model.entity.CommonCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonCodeRepository extends JpaRepository<CommonCodeEntity, Long> {

    boolean existsByGroupCodeAndCode(String groupCode, String code);

}
