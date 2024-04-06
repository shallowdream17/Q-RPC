package com.waiq.rpc.exception;

import com.waiq.rpc.enumeration.RpcError;

public class RpcException extends RuntimeException {
    public RpcException(RpcError error,String detail){
        super(error.getMessage()+":"+detail);
    }
    public RpcException(String message,Throwable cause){
        super(message,cause);
    }
    public RpcException(RpcError rpcError){
        super(rpcError.getMessage());
    }

}
