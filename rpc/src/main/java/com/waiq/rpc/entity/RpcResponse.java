package com.waiq.rpc.entity;

import com.waiq.rpc.enumeration.ResponseCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResponse<T> implements Serializable {
    //状态相应码
    private Integer statusCode;
    //响应状态补充信息
    private String message;
    //回应数据
    private T data;

    public static<U> RpcResponse<U> success(U data){
        RpcResponse<U> rpcResponse = new RpcResponse<>();
        rpcResponse.setData(data);
        rpcResponse.setStatusCode(ResponseCode.SUCCESS.getCode());
        return rpcResponse;
    }

    public static<U> RpcResponse<U> fail(ResponseCode code){
        RpcResponse<U> rpcResponse = new RpcResponse<>();
        rpcResponse.setMessage(code.getMessage());
        rpcResponse.setStatusCode(code.getCode());
        return rpcResponse;
    }
}
