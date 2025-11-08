package org.groupware.domain.common.repository;

import java.util.List;
import org.groupware.domain.common.model.CommonCode;

public interface CommonCodeRepository {

    List<CommonCode> findCommonCodeByGroupCode(String groupCode);

}
