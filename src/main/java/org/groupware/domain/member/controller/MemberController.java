package org.groupware.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.groupware.common.response.Response;
import org.groupware.domain.auth.dto.req.CreateMemberReq;
import org.groupware.domain.member.dto.req.MemberRes;
import org.groupware.domain.member.service.MemberService;
import org.groupware.global.principal.AuthPrincipal;
import org.groupware.global.principal.MemberAuth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Member", description = "회원 관리 API")
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/create")
    @Operation(
        summary = "사용자 생성",
        description = "사용자를 생성한다",
        responses = {
            @ApiResponse(responseCode = "200", description = "사용자 생성 성공")
        }
    )
    public Response<Void> createMember(@Parameter(hidden = true) @AuthPrincipal MemberAuth user, @RequestBody @Valid CreateMemberReq req) {
        memberService.createMember(req);
        return Response.ok(null);
    }

    @GetMapping
    @Operation(
        summary = "회원정보 조회",
        description = "특정 회원 정보를 조회한다.",
        parameters = {
            @Parameter(name = "memberId", description = "회원 ID", in = ParameterIn.PATH, required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공", content = @Content(schema = @Schema(implementation = MemberRes.class)))
        }
    )
    public Response<MemberRes> getMember(@Parameter(hidden = true) @AuthPrincipal MemberAuth user, String memberId) {
        return Response.ok(memberService.getMember(memberId));
    }

}
