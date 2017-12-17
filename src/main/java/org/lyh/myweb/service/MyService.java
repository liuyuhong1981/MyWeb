/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年1月18日
 */
package org.lyh.myweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    MessageService service;
    
    public void test() {
//        try {
//            Thread.sleep(10000l);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("run service.test()");
//        service.setMsg("my service 11111");
//        service.printMsg();
    }
}
