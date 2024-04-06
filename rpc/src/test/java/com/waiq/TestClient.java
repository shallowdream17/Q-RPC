package com.waiq;

import com.waiq.rpc.api.HelloObject;
import com.waiq.rpc.api.HelloService;
import com.waiq.rpc.client.RpcClient;
import com.waiq.rpc.client.RpcClientProxy;
import com.waiq.rpc.entity.RpcRequest;
import com.waiq.rpc.entity.RpcResponse;

public class TestClient {
    public static void main(String[] args) throws NoSuchMethodException {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1",9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(17,"This is a message 3");
        String res = helloService.hello(object);
        System.out.println(res);
//        RpcRequest rpcRequest = RpcRequest.builder()
//                .interfaceName(HelloService.class.getName())
//                .methodName(HelloService.class.getMethod("hello",HelloObject.class).getName())
//                .parameters(new HelloObject[]{object})
//                .paramTypes(HelloService.class.getMethod("hello", HelloObject.class).getParameterTypes())
//                .build();
//        RpcClient rpcClient =new RpcClient();
//        RpcResponse rpcResponse =(RpcResponse)rpcClient.sendRequest(rpcRequest,"127.0.0.1",9000);
//        System.out.println(rpcResponse.getData());
    }
}
