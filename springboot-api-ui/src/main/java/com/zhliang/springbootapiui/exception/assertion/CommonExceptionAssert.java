package com.zhliang.springbootapiui.exception.assertion;

import com.zhliang.springbootapiui.exception.ArgumentException;
import com.zhliang.springbootapiui.exception.BaseException;
import com.zhliang.springbootapiui.exception.IResponseEnum;

import java.text.MessageFormat;

/**
 * @Author: zhliang
 * @Date: 2020/5/6 09:57
 * @Description:
 * @Version: V1.0
 */
public interface CommonExceptionAssert extends Assert , IResponseEnum{

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new ArgumentException(this, args, msg, t);
    }
}
