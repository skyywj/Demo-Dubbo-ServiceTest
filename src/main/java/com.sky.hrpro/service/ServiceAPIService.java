package com.sky.hrpro.service;

import api.demo.DemoInterface;
import api.demo.bean.DemoBean;
import com.alibaba.dubbo.config.annotation.Reference;
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
