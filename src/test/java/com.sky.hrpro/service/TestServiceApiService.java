package com.sky.hrpro.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: CarryJey
 * @Date: 2018/10/7 12:28:00
 */
@SpringBootTest
@Component
@RunWith(SpringRunner.class)
public class TestServiceApiService {

    @Autowired
    private  ServiceAPIService serviceAPIService;

    @Test
    public void testServiceApi(){
        serviceAPIService.testServiceApi(40);
    }

}
