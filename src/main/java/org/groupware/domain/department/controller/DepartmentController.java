package org.groupware.domain.department.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.groupware.common.response.Response;
import org.groupware.domain.department.dto.res.DepartmentListRes;
import org.groupware.domain.department.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Department", description = "부서 관리 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping
    @Operation(
        summary = "부서 정보 조회",
        description = "부서 정보를 조회한다.",
        parameters = {
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "부서 조회 성공", content = @Content(schema = @Schema(implementation = DepartmentListRes.class)))
        }
    )
    public Response<DepartmentListRes> getDepartmentList() {
        DepartmentListRes departmentList = departmentService.findDepartmentList();

        return Response.ok(departmentList);
    }



}
