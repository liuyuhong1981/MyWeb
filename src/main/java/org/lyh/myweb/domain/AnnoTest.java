/**
 * Created By Liu Yuhong - 2017年9月28日
 */
package org.lyh.myweb.domain;

import org.lyh.myweb.annotation.MyAnno;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年9月28日
 */
public class AnnoTest {

    @MyAnno(name="t_id")
    String id;

    @MyAnno(name="t_name")
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
