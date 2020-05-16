package com.zhliang.springboot.api.mideng.token;

import com.zhliang.springboot.api.mideng.constant.Constant;
import com.zhliang.springboot.api.mideng.exception.ServiceException;
import com.zhliang.springboot.api.mideng.redis.RedisService;
import com.zhliang.springboot.api.mideng.utils.RandomUtil;
import com.zhliang.springboot.api.mideng.utils.StrUtil;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.token
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 15:08
 * @version：V1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisService redisService;

    /**
     * 创建token
     */
    @Override
    public String createToken() {
        String str = RandomUtil.randomUUID();
        StrBuilder token = new StrBuilder();
        try {
            token.append(Constant.Redis.TOKEN_PREFIX).append(str);
            redisService.setEx(token.toString(), token.toString(),10000L);
            boolean notEmpty = StrUtil.isNotEmpty(token.toString());
            if (notEmpty) {
                return token.toString();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 检验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {

        String token = request.getHeader(Constant.TOKEN_NAME);
        if (StrUtil.isBlank(token)) {// header中不存在token
            token = request.getParameter(Constant.TOKEN_NAME);
            if (StrUtil.isBlank(token)) {// parameter中也不存在token
                throw new ServiceException(Constant.ResponseCode.ILLEGAL_ARGUMENT, 100);
            }
        }

        if (!redisService.exists(token)) {
            throw new ServiceException(Constant.ResponseCode.REPETITIVE_OPERATION, 200);
        }

        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new ServiceException(Constant.ResponseCode.REPETITIVE_OPERATION, 200);
        }
        return true;
    }
}
