package com.waiq.rpc.registry;

public interface ServiceRegistry {
    //注册服务信息
    <T>void registry(T service);
    //获取服务信息
    Object getService(String serviceName);
}
