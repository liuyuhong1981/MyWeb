/**
 * Created By Liu Yuhong - 2017年10月21日
 */
package org.lyh.myweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lyh.myweb.service.MyService;
import org.lyh.myweb.service.MyService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class MyServiceTest {
    
    @Autowired
    MyService service;

    @Autowired
    MyService2 service2;

    @Test
    public void test1() {
        service.test();
        service2.test();
    }
    
    public void test2() {
        
    }
}
