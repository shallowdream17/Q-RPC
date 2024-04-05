package com.waiq;

import com.waiq.rpc.api.HelloObject;
import com.waiq.rpc.api.HelloService;
import com.waiq.rpc.client.RpcClientProxy;

public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1",9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(17,"This is a message 2");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
