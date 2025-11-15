package org.groupware.domain.department.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.groupware.domain.department.model.Department;

public record DepartmentListRes (
    @Schema(description = "부서 목록", type = "Department")
    List<Department> departments
) {

    public DepartmentListRes(List<Department> departments) {
        this.departments = departments;
    }

}