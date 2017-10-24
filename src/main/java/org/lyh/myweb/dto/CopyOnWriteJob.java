/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb.dto;

import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyOnWriteJob implements Runnable {
    Logger logger = LoggerFactory.getLogger(CopyOnWriteJob.class);
    private User user = new User();

    private CopyOnWriteArrayList<String> list;

    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            logger.info("删除list内容：{}", list.get(i));
        }
    }

    public int fix(int y) {
        return user.fix(y);
    }

    public CopyOnWriteArrayList<String> getList() {
        if(list == null) {
            list = new CopyOnWriteArrayList<String>();
        }
        return list;
    }

    public void setList(CopyOnWriteArrayList<String> list) {
        this.list = list;
    }

}
