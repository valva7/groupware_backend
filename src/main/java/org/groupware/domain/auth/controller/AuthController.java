package org.groupware.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.groupware.domain.auth.dto.req.LoginReq;
import org.groupware.domain.auth.dto.res.BaseRoleRes;
import org.groupware.global.exception.InvalidJwtException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.groupware.domain.auth.dto.req.NewAccessTokenReq;
import org.groupware.domain.auth.dto.res.LoginTokenRes;
import org.groupware.domain.auth.dto.res.NewAccessTokenRes;
import org.groupware.domain.auth.service.AuthService;
import org.groupware.domain.auth.service.TokenProvider;
import org.groupware.global.exception.ErrorCode;
import org.groupware.common.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 관련 API")
@RequestMapping("/auth")
@Validated
@RestController
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthService authService;

    public AuthController(TokenProvider tokenProvider, AuthService authService) {
        this.tokenProvider = tokenProvider;
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(
        summary = "로그인",
        description = "로그인을 수행한다.",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = LoginTokenRes.class))
            )
        }
    )
    public Response<LoginTokenRes> login(@RequestBody @Valid LoginReq req) {
        LoginTokenRes loginTokenRes = authService.login(req);
        return Response.ok(loginTokenRes);
    }

    @GetMapping("/check")
    @Operation(
        summary = "AccessToken 확인",
        description = "AccessToken이 유효한지 확인한다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "유효 여부 확인 성공")
        }
    )
    public Response<Void> loginPage(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (!tokenProvider.validateToken(token)) {
            return Response.error(ErrorCode.UNAUTHORIZED);
        }

        return Response.ok(null);
    }

    @PostMapping("/refresh")
    @Operation(
        summary = "AccessToken 재발급",
        description = "RefreshToken으로 AccessToken을 재발급한다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "AccessToken 발급 성공", content = @Content(schema = @Schema(implementation = NewAccessTokenRes.class)))
        }
    )
    public Response<NewAccessTokenRes> refreshAccessToken(@RequestBody @Valid NewAccessTokenReq request) {
        String refreshToken = request.refreshToken();

        String newAccessToken = null;
        if (tokenProvider.validateToken(refreshToken)) {
            newAccessToken = tokenProvider.createNewAccessToken(refreshToken);
        } else {
            throw new InvalidJwtException(ErrorCode.UNAUTHORIZED);
        }

        return Response.ok(new NewAccessTokenRes(newAccessToken));
    }

    @GetMapping("/role")
    @Operation(
        summary = "권한 목록 조회",
        description = "권한 목록을 조회한다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "권한 목록 조회 성공", content = @Content(schema = @Schema(implementation = BaseRoleRes.class)))
        }
    )
    public Response<BaseRoleRes> getRoleList() {
        return Response.ok(new BaseRoleRes(authService.findRoleList()));
    }

}
