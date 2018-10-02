package com.sky.hrpro.util;

import io.grpc.Metadata;

/**
 * @Author: CarryJey
 * @Date: 2018/10/2 11:26:38
 */
public abstract class Constants {
    private Constants(){}

    public static final String HEADER_NAME_REQUEST_ID = "X-Request-Id";
    public static final String HEADER_NAME_REQUEST_IP = "X-Real-IP";

    public static final Metadata.Key<String> HEADER_KEY_REQUEST_ID =
            Metadata.Key.of(HEADER_NAME_REQUEST_ID, Metadata.ASCII_STRING_MARSHALLER);

}
