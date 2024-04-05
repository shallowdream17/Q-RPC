package com.waiq;

import com.waiq.rpc.api.HelloService;
import com.waiq.rpc.server.RpcServer;
import com.waiq.test.HelloServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService,9000);
    }
}
