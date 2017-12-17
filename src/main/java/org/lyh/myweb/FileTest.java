/**
 * Created By Liu Yuhong - 2017年12月17日
 */
package org.lyh.myweb;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年12月17日
 */
public class FileTest {

    /**<pre>
     * 
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月17日
     * @param args
     */
    public static void main(String[] args) {
        deleteAllImageFilesInCorpDir("C:/ProjectSrc/git/MySamples/ThirdPartTools/DXCOCR/result");
    }

    /**
     * <pre>
     * 删除厂商目录下全部文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月17日
     * @param dirPath
     */
    private static void deleteAllImageFilesInCorpDir(String rootResultPath) {
        File dir = new File(getCurrentMonthFolderPath(rootResultPath));
        if (dir.exists()) {
            File[] rootFiles = dir.listFiles();
            for (File file : rootFiles) {
                if (file.isDirectory()) {
                    File[] corpFiles = file.listFiles();
                    for (File corpFile : corpFiles) {
                        corpFile.delete();
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * 取得本月的文件夹路径: rootPath/yyyy/MM/
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param rootResultPath
     * @return
     */
    private static String getCurrentMonthFolderPath(String rootResultPath) {
        StringBuffer currentMonthPath = new StringBuffer();
        return currentMonthPath.append(rootResultPath).append(File.separator).append(getCurrentDateByFormat("yyyy")).append(File.separator)
                .append(getCurrentDateByFormat("MM")).append(File.separator).toString();
    }

    /**
     * <pre>
     * 取得指定格式的当前日期
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @return
     */
    private static String getCurrentDateByFormat(String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(cal.getTime());
    }

}
