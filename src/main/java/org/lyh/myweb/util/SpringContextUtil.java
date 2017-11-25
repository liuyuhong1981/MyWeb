/**
 * Created By Liu Yuhong - 2017年11月25日
 */
package org.lyh.myweb.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月25日
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtil.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
