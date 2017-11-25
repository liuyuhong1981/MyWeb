/**
 * Created By Liu Yuhong - 2017年11月24日
 */
package org.lyh.myweb.service;

import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月24日
 */
@Service("TestService1")
public class TestService1 implements BaseService {

    public String name = "TestService1";

    @Override
    public String getName() {
        return this.name;
    }
}
