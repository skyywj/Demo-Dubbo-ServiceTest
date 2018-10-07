package com.sky.hrpro.service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.sky.hypro.service.DemoBean;
import com.sky.hypro.service.DemoInterface;
import org.springframework.stereotype.Service;

/**
 * @Author: CarryJey
 * @Date: 2018/10/7 12:22:09
 */

/**
 * 测试Demo-Dubbo & Service-API的类
 *
 */

@Service
public class ServiceAPIService {

    @Reference(version = "1.0.0")
    private DemoInterface demoInterface;

    public void testServiceApi(int id ){
        DemoBean demoBean = demoInterface.getDemoBean(id);
        System.out.println(demoBean);
    }

}
