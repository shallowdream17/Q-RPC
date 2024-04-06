package com.waiq.rpc.server;

import com.waiq.rpc.entity.RpcRequest;
import com.waiq.rpc.entity.RpcResponse;
import com.waiq.rpc.registry.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 处理线程、接收对象
 */
@Slf4j
public class RequestHandlerThread implements Runnable{
    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceRegistry serviceRegistry;
    public RequestHandlerThread(Socket socket,RequestHandler requestHandler,ServiceRegistry serviceRegistry){
        this.socket=socket;
        this.requestHandler=requestHandler;
        this.serviceRegistry=serviceRegistry;
    }
    @Override
    public void run() {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())){
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object result = requestHandler.handle(rpcRequest,service);
            objectOutputStream.writeObject(RpcResponse.success(result));
            objectOutputStream.flush();
        }catch (IOException | ClassNotFoundException e){
            log.error("调用或发送时有错误发生: {}",e);
        }
    }
}
