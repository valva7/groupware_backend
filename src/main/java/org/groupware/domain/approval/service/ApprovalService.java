package org.groupware.domain.approval.service;


import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.approval.repository.ApprovalRequestRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApprovalService {

    private ApprovalRequestRepository approvalRequestRepository;

    public ApprovalService(ApprovalRequestRepository approvalRequestRepository) {
        this.approvalRequestRepository = approvalRequestRepository;
    }



}
