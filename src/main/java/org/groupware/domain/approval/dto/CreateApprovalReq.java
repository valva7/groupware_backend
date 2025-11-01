package org.groupware.domain.approval.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.groupware.global.annotation.FileValidate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CreateApprovalReq(

    // ===== 결재 기본 정보 =====
    @NotBlank(message = "결재 타입은 필수입니다.")
    String type,

    @NotBlank(message = "제목은 필수입니다.")
    String title,

    String content,


    // ===== 결재선 =====
    @NotNull(message = "결재선 정보는 필수입니다.")
    List<Approver> approvers,


    // ===== 참조자 =====
    List<Reference> references,


    // ===== 휴가신청서 =====
    List<VacationSchedule> vacationSchedules,


    // ===== 구매요청서 =====
    String itemName,
    Integer quantity,
    Integer unitPrice,


    // ===== 교육신청서 =====
    String educationName,
    LocalDate educationDate,
    Integer educationCost,


    // ===== 지출결의서 =====
    String expenseCategory,
    Integer amount,
    String vendor,


    // ===== 첨부파일 =====
    @FileValidate(
        allowedTypes = {"xls", "xlsx"},
        fileType = "AU",
        message = "업로드할 수 없는 파일 형식입니다."
    )
    List<MultipartFile> attachedFiles

) {

    // === 결재자 정보 ===
    public record Approver(
        @NotNull(message = "결재자 ID는 필수입니다.")
        Long memberId,

        @NotNull(message = "결재 순서는 필수입니다.")
        Integer sequence
    ) { }

    // === 참조자 정보 ===
    public record Reference(
        @NotNull(message = "참조자 ID는 필수입니다.")
        Long memberId
    ) { }

    // === 휴가신청서 일정 ===
    public record VacationSchedule(
        String id,

        @NotBlank(message = "휴가 유형은 필수입니다.")
        String type,

        @NotNull(message = "시작일은 필수입니다.")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate endDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime startTime,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime endTime,

        String reason
    ) { }
}
