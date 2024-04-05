package com.waiq.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 方法调用的状态响应码
 */

@AllArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS(200,"方法调用成功"),
    FAIL(500,"方法调用失败"),
    METHOD_NOT_FOUND(500,"未找到指定方法"),
    CLASS_NOT_FOUND(500,"未找到指定类");

    private final int code;
    private final String message;


}
