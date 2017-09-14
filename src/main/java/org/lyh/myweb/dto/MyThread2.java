/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb.dto;

public class MyThread2 implements Runnable {
    private int n;

    public MyThread2(int n) {
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(n + ":" + i);
        }
    }
}