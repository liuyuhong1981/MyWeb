/**
 * Created By Liu Yuhong - 2017年11月24日
 */
package org.lyh.myweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月24日
 */
@Service("TestService222")
public class TestService2 implements BaseService {
    
    @Autowired
    MessageService messageService;

    public String name = "TestService2";

    @Override
    public String getName() {
        return this.name;
    }
}
