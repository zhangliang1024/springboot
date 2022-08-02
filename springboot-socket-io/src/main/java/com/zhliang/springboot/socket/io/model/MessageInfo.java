package com.zhliang.springboot.socket.io.model;

import org.springframework.stereotype.Component;

/**
 * @ClassNameMessageInfo
 * @Description TODO
 * @Author DELL
 * @Date 2022/1/2114:36
 * @Version 1.0
 **/
@Component
public class MessageInfo {
    private String userID;
    private String userName;
    private String message;

    public MessageInfo() {
    }

    public MessageInfo(String userID, String userName, String message) {
        this.userID = userID;
        this.userName = userName;
        this.message = message;
    }

    public MessageInfo(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
