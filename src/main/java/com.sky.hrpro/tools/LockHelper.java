package com.sky.hrpro.tools;

import com.sky.hrpro.EnumList.ErrorCode;
import com.sky.hrpro.HrProsApplication;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CarryJey
 * @Date: 2018/9/30 14:40:27
 */

/**
 * Desc:
 * 采用redisson支持的Rlock（可重入锁），进行加锁处理
 * 锁成功则执行锁成功的事件，锁不成功时执行获取锁失败的提醒
 * 使用场景：批量更新时常用
 */
@Component
public class LockHelper {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private HrProsApplication.RedisConfig redisConfig;

    @Value("0")
    private int waitTime;

    @Value("30")
    private int leaseTime;

    public void runWithLock(Runnable lockSuccess){
        String  name = "lock:update test";
        try{
            runWithLock(
                    name,
                    () -> logger.warn("lock failed,error code:{}",ErrorCode.ERROR_SYNC_IN_PROGRESS),
                    lockSuccess
            );
        }catch (InterruptedException e){
            logger.warn("interrupt wheen trying acquire lock");
            //当前线程中断
            Thread.currentThread().interrupt();
        }
    }

    public void runWithLock(String name,Runnable lockFail,Runnable lockSuccess) throws InterruptedException {

        RLock rLock = redissonClient.getLock(redisConfig.addPrefix(name));
        boolean islock = false;
        try{
            if(!rLock.tryLock(waitTime,leaseTime,TimeUnit.SECONDS)){
                lockFail.run();
            }
            lockSuccess.run();
        }finally{
            if(islock){
                rLock.unlock();
            }
        }
    }
}
