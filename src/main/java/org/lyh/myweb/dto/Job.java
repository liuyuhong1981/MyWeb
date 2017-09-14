/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb.dto;

public class Job implements Runnable {
    private User user = new User();

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            this.fix(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : 当前user对象的x值= " + user.getX());
        }
    }

    public int fix(int y) {
        return user.fix(y);
    }
}
