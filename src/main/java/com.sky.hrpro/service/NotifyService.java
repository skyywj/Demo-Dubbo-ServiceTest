package com.sky.hrpro.service;

import com.sky.hrpro.notify.EventDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.lang.invoke.MethodHandles;

/**
 * 事件监听类
 * @Author: CarryJey
 * @Date: 2018/10/1 22:36:37
 */
@Service
public class NotifyService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Demo监听事件处理方法
     * 从方法参数中获取事件类型
     */
    @TransactionalEventListener(fallbackExecution = true)
    @Async("pushNotifyExecutor")
    public void DemoHandle(EventDemo event) {
        logger.info("handle demo event: {}.", event);
        //处理逻辑
    }

}
