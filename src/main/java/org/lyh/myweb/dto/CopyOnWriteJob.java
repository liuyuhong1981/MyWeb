/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb.dto;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyOnWriteJob implements Runnable {

    Logger logger = LoggerFactory.getLogger(CopyOnWriteJob.class);

    private String id;

    private boolean isFinish;

    private CopyOnWriteArrayList<String> list;

    @Override
    public void run() {
        while (!isFinish) {
//            for (int i = 0; i < list.size(); i++) {
//                logger.info("删除list内容：{}", list.get(i));
//                list.remove(i);
//            }
            Iterator<String> it = list.iterator();
            int i = 0;
            while(it.hasNext()) {
              logger.info("删除list内容：{}", it.next());
              list.remove(i);
            }
        }
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public CopyOnWriteArrayList<String> getList() {
        if (list == null) {
            list = new CopyOnWriteArrayList<String>();
        }
        return list;
    }

    public void setList(CopyOnWriteArrayList<String> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
