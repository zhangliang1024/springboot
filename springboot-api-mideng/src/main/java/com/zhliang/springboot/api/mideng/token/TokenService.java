package com.zhliang.springboot.api.mideng.token;

import javax.servlet.http.HttpServletRequest;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.token
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 15:07
 * @version：V1.0
 */
public interface TokenService {

    /**
     * 创建token
     * @return
     */
    String createToken();

    /**
     * 检验token
     * @param request
     * @return
     */
    boolean checkToken(HttpServletRequest request) throws Exception;

}
