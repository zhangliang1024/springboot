package com.zhliang.springboot.rest.api.common.assertion;


import com.zhliang.springboot.rest.api.common.enums.IResponseEnum;
import com.zhliang.springboot.rest.api.common.exception.BaseException;
import com.zhliang.springboot.rest.api.common.exception.BusinessException;

import java.text.MessageFormat;

/**
 * 业务断言异常
 * @author hutao
 * @date 2019-11-06 17:02
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable cause, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, cause);
    }
}
