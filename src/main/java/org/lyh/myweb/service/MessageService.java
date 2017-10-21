/**
 * Created By Liu Yuhong - 2017年10月21日
 */
package org.lyh.myweb.service;

import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月21日
 */
@Service
public class MessageService {

    private String msg;

    public void printMsg() {
        System.out.println("test msg: " + msg);
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
