package org.groupware.domain.common.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.common.dto.res.CommonCodeRes;
import org.groupware.domain.common.model.CommonCode;
import org.groupware.domain.common.repository.CommonCodeRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonService {

    private final CommonCodeRepository commonCodeRepository;


    public CommonCodeRes findCommonCodeByGroupCode(String groupCode) {
        List<CommonCode> commonCodes = commonCodeRepository.findCommonCodeByGroupCode(groupCode);

        return new CommonCodeRes(commonCodes);
    }

}
