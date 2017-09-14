/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb;

interface ClassCastFunction<T, User> {
    T build(User user, User user2);
}