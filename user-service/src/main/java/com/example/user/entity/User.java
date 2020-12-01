package com.example.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:50 on 2020/11/17
 * @version V0.1
 * @classNmae User
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private int userId;
    private String userUuid;
    private String userName;
    private String portraitUrl;
    private String loginPwd;
    private String mobile;
    private int status;
    private String email;
    private int points;
    private int level;
    private long createTime;
    private long updateTime;



}
