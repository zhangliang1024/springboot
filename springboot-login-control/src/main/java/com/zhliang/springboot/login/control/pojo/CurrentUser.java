package com.zhliang.springboot.login.control.pojo;

/**
 * @Author: colin
 * @Date: 2019/10/21 10:14
 * @Description:
 * @Version: V1.0
 */
public class CurrentUser {

    private static final ThreadLocal<UserBO> currentUser = new ThreadLocal<>();

    public static void put(UserBO userBO) {
        currentUser.set(userBO);
    }

    public static UserBO get() {
        return currentUser.get();
    }
}
