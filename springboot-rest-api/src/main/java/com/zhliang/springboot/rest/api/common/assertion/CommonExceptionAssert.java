package com.zhliang.springboot.rest.api.common.assertion;

import com.zhliang.springboot.rest.api.common.assertion.Assert;
import com.zhliang.springboot.rest.api.common.enums.IResponseEnum;
import com.zhliang.springboot.rest.api.common.exception.ArgumentException;
import com.zhliang.springboot.rest.api.common.exception.BaseException;

import java.text.MessageFormat;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.common.enums
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/15 16:09
 * @version：V1.0
 */
public interface CommonExceptionAssert extends IResponseEnum, Assert {


    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable cause, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new ArgumentException(this, args, msg, cause);
    }
}
