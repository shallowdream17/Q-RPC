package com.waiq.rpc.registry;

import com.waiq.rpc.enumeration.RpcError;
import com.waiq.rpc.exception.RpcException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class DefaultServiceRegistry implements ServiceRegistry{

    //zuo
    private final Map<String,Object> serviceMap = new ConcurrentHashMap<>();
    //线程安全的set
    private final Set<String> registeredService = ConcurrentHashMap.newKeySet();

    @Override
    public <T> void registry(T service) {
        String serviceName = service.getClass().getCanonicalName();
        if(registeredService.contains(serviceName)) {
            return;
        }
        registeredService.add(serviceName);
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if(interfaces.length==0){
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }
        //某个接口只能有一个对象提供服务
        for(Class<?> i:interfaces){
            serviceMap.put(i.getCanonicalName(),service);
        }
        log.info("向接口: {} 注册服务: {}",interfaces,serviceName);

    }
    @Override
    public Object getService(String serviceName) {
        Object object = serviceMap.get(serviceName);
        if(object==null){
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return object;
    }
}
