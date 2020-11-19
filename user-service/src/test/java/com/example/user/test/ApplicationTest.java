package com.example.user.test;

import com.example.user.UserApplication;
import com.example.user.service.IUserService;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:42 on 2020/11/19
 * @version V0.1
 * @classNmae ApplicationTest
 */
public class ApplicationTest {

    @Test
    public void jdbc_test(){

        ConfigurableApplicationContext application = SpringApplication.run(UserApplication.class);

        IUserService userService = application.getBean(IUserService.class);

        System.out.println(userService.select(1));

    }
}
