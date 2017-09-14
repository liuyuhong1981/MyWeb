/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    String name;
    int age;
    private int x = 100;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getX() {
        return x;
    }

    public int fix(int y) {
        x = x - y;
        return x;
    }

    public synchronized int getXSync() {
        return x;
    }

    public synchronized int fixSync(int y) {
        x = x - y;
        return x;
    }
}