package org.groupware.domain.approval.repository;

import org.groupware.domain.approval.model.entity.ApprovalRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaApprovalRequestRepository extends JpaRepository<ApprovalRequestEntity, Long> {

}
