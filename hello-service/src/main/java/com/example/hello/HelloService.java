package com.example.hello;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:52 on 2020/11/2
 * @version V0.1
 * @classNmae HelloService
 */
public class HelloService implements IService {

    private String serviceName ;

    public HelloService(String serviceName){
        this.serviceName=serviceName;
    }
    @Override
    public String doService() {

        return "Hello Netty From "+serviceName;
    }
}
