package com.sky.hrpro.notify;

/**
 * @Author: CarryJey
 * @Date: 2018/9/27 上午10:50
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import java.lang.invoke.MethodHandles;

/**
 * 事件
 */
public class TestPublishDemo {

    private static final Logger logger=  LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void  publishEventDemo(){
        logger.info("publish demo 。。。");
        /**
         * 事件发布
         */
        eventPublisher.publishEvent(new EventDemo("demo"));
    }

}
