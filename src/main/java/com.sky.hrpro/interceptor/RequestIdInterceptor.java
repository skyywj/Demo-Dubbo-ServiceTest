package com.sky.hrpro.interceptor;

import com.sky.hrpro.util.Constants;
import com.sky.hrpro.util.StringUtils;
import com.sky.hrpro.util.ValueUtil;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.ForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.springframework.core.annotation.Order;


/**
 * 添加 request id 的拦截器
 *
 * @author CarryJey
 * @since 2018-10-2
 */
@GRpcGlobalInterceptor
@Order(2)
public class RequestIdInterceptor implements ServerInterceptor {

    private static final Context.Key<String> REQUEST_ID_KEY = Context.key("REQUEST_ID_KEY");

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        // envoy 会生成 request id
        String requestId = headers.get(Constants.HEADER_KEY_REQUEST_ID);
        if (StringUtils.isEmpty(requestId)) {
            requestId = ValueUtil.getUuid();
        }

        String finalRequestId = requestId;

        return Contexts.interceptCall(
            Context.current().withValue(REQUEST_ID_KEY, requestId),
            new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
                private boolean isRequestIdSet = false;

                @Override
                public void sendHeaders(Metadata headers) {
                    headers.put(Constants.HEADER_KEY_REQUEST_ID, finalRequestId);
                    isRequestIdSet = true;
                    super.sendHeaders(headers);
                }

                @Override
                public void close(Status status, Metadata trailers) {
                    if (!isRequestIdSet) {
                        trailers.put(Constants.HEADER_KEY_REQUEST_ID, finalRequestId);
                    }

                    super.close(status, trailers);
                }
            },
            headers,
            next);
    }

    public static String getRequestId() {
        //Request_id_key拦截的是grpc请求
        String requestId = REQUEST_ID_KEY.get();
        if (requestId != null) {
            return requestId;
        }

        return "n/a";
    }
}
