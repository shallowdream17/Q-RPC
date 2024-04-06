package com.waiq;

import com.waiq.rpc.api.HelloService;
import com.waiq.rpc.registry.DefaultServiceRegistry;
import com.waiq.rpc.registry.ServiceRegistry;
import com.waiq.rpc.server.RpcServer;
import com.waiq.test.HelloServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.registry(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
