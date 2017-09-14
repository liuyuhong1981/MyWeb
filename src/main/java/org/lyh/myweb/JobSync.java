/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb;

import org.lyh.myweb.dto.User;

class JobSync implements Runnable {
    private User user = new User();

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            this.fix(1);
            System.out.println(Thread.currentThread().getName() + " : 当前user对象的x值= " + user.getXSync());
        }
    }

    public int fix(int y) {
        return user.fixSync(y);
    }
}