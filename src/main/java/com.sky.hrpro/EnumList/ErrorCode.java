package com.sky.hrpro.EnumList;

/**
 * Desc:错误码定义
 *
 * @Author: CarryJey
 * @Date: 2018/9/30 14:17:11
 */
public enum ErrorCode {
    //服务端错误
    ERROR_SERVER_ERROR(0),
    //超时错误
    ERROR_TIME_OUT(1),
    //加锁失败
    ERROR_SYNC_IN_PROGRESS(2);


    private final int value;
    private ErrorCode(int value){
        this.value = value;
    }
}
