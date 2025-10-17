package org.groupware.domain.push.repository;

import org.groupware.domain.push.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFcmPushRepository extends JpaRepository<FcmTokenEntity, Long> {

}
