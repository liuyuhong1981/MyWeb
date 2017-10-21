/**
 * Created By Liu Yuhong - 2017年10月21日
 */
package org.lyh.myweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月21日
 */
@Service
public class MyService2 {

    @Autowired
    MessageService service;
    
    public void test() {
        System.out.println("run service2.test()");
        service.printMsg();
    }
}
