package com.waiq.test;

import com.waiq.rpc.api.HelloObject;
import com.waiq.rpc.api.HelloService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(HelloObject helloObject) {
        log.info("接收到{}",helloObject.getMessage());
        return "这是调用的返回值,id="+helloObject.getId();
    }
}
