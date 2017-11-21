/**
 * Created By Liu Yuhong - 2017年11月21日
 */
package org.lyh.myweb.dto;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月21日
 */
public class TestThreadJob implements Runnable {

    private String id;

    @Override
    public void run() {
        System.out.println("Job start: " + id);
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Job finish: " + id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        TestThreadJob target = (TestThreadJob) obj;
        if (this.id.equalsIgnoreCase(target.getId())) {
            return true;
        } else {
            return false;
        }
    }
}
