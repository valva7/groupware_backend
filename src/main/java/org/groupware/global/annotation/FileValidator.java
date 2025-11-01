package org.groupware.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.util.List;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<FileValidate, MultipartFile> {

    private static final List<String> ALLOWED_EXTENSIONS = List.of("xls", "xlsx");
    private static final List<String> ALLOWED_MIME_TYPES = List.of("");

    private final Tika tika = new Tika();
    private String[] allowedTypes;
    private String fileType;

    @Override
    public void initialize(FileValidate constraintAnnotation) {
        this.allowedTypes = constraintAnnotation.allowedTypes();
        this.fileType = constraintAnnotation.fileType();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        switch (fileType)
        {
            // 업로드 유형
            case "AU" -> {  // 전자결재
                if (allowedTypes.length > 0) {
                    return MisicFileValidation(file);
                } else {
                    return true;
                }
            }
        }

        return true;
    }

    private boolean MisicFileValidation(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        // 확장자가 없는 경우
        if (originalFilename == null || !originalFilename.contains(".")) {
            return false;
        }

        // 확장자 추출
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();

        // MIME 타입 추출
        String mimeType = null;
        try {
            mimeType = tika.detect(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 확장자 및 MIMETYPE이 허용된 타입 중 하나인지 확인
        return ALLOWED_EXTENSIONS.contains(extension.toLowerCase()) && ALLOWED_MIME_TYPES.contains(mimeType);
    }

}
