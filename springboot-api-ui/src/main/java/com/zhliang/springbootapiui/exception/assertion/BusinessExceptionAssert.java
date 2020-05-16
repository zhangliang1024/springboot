package com.zhliang.springbootapiui.exception.assertion;

import com.zhliang.springbootapiui.exception.BaseException;
import com.zhliang.springbootapiui.exception.BusinessException;
import com.zhliang.springbootapiui.exception.IResponseEnum;

import java.text.MessageFormat;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springbootapiui.exception.assertion
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 09:55
 * @version：V1.0
 */
public interface BusinessExceptionAssert extends IResponseEnum,Assert {

    @Override
    default BaseException newException(Object... args){
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this,args,msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args){
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, t);
    }
}
