package org.groupware.domain.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.groupware.common.response.Response;
import org.groupware.domain.common.dto.res.CommonCodeRes;
import org.groupware.domain.common.service.CommonService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Common Code", description = "공통 코드 관련 API")
@RequestMapping("/common-code")
@Validated
@RequiredArgsConstructor
@RestController
public class CommonController {

    private final CommonService commonService;


    @GetMapping("/common-codes/{groupCode}")
    @Operation(
        summary = "그룹코드로 공통 코드 리스트를 조회한다.",
        description = "그룹코드로 공통 코드 리스트를 조회한다.",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "공통코드 조회 성공", content = @Content(schema = @Schema(implementation = CommonCodeRes.class))
            )
        }
    )
    public Response<CommonCodeRes> getCommonCode(@PathVariable("groupCode") String groupCode) {
        CommonCodeRes commonCodeRes = commonService.findCommonCodeByGroupCode(groupCode);

        return Response.ok(commonCodeRes);
    }

}
