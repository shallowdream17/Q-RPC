package com.waiq.rpc.server;

import com.waiq.rpc.entity.RpcRequest;
import com.waiq.rpc.entity.RpcResponse;
import com.waiq.rpc.enumeration.ResponseCode;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class RequestHandler {
    public Object handle(RpcRequest rpcRequest,Object service){
        Object result = null;
        try{
            result = invokeTargetMethod(rpcRequest,service);
            log.info("服务:{}成功调用方法:{}",rpcRequest.getInterfaceName(),rpcRequest.getMethodName());
        }catch (IllegalAccessException | InvocationTargetException e){
            log.error("调用或发送时有错误发生: {}",e);
        }
        return result;
    }

    public Object invokeTargetMethod(RpcRequest rpcRequest,Object service) throws InvocationTargetException, IllegalAccessException {
        Method method;
        try{
            method = service.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParamTypes());
        } catch (NoSuchMethodException e) {
            return RpcResponse.fail(ResponseCode.METHOD_NOT_FOUND);
        }
        return method.invoke(service,rpcRequest.getParameters());
    }
}
