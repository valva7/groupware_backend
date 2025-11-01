package org.groupware.domain.approval.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.groupware.common.response.Response;
import org.groupware.domain.approval.dto.CreateApprovalReq;
import org.groupware.domain.approval.service.ApprovalService;
import org.groupware.global.principal.AuthPrincipal;
import org.groupware.global.principal.MemberAuth;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Aooroval", description = "전자결재 API")
@RequestMapping("/approval")
@Validated
@RestController
public class ApprovalController {

    private ApprovalService approvalService;

    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @PostMapping("/create")
    @Operation(
        summary = "전자결재 생성",
        description = "전자결재를 생성한다",
        responses = {
            @ApiResponse(responseCode = "200", description = "전자결재 생성 성공")
        }
    )
    public Response<Void> createApproval(@Parameter(hidden = true) @AuthPrincipal MemberAuth user, @ModelAttribute @Valid CreateApprovalReq req) {
        //approvalService.createMember(req);
        return Response.ok(null);
    }

}
