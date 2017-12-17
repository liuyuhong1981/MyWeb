/**
 * Created By Liu Yuhong - 2017年12月17日
 */
package org.lyh.myweb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年12月17日
 */
public class ExcelTest {

    /**<pre>
     * 
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月17日
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        File file = new File("c:\\temp\\workbook.xls");

        FileOutputStream fileOut = new FileOutputStream("c:\\temp\\workbook.xls");
        Workbook wb = null;
        Sheet sheet1 = null;
        if (file.exists()) {
            wb = new HSSFWorkbook(new FileInputStream("c:\\temp\\workbook.xls"));
            new File("c:\\temp\\workbook.xls").delete();
            sheet1 = wb.getSheetAt(0);
        } else {
            wb = new HSSFWorkbook();
            sheet1 = wb.createSheet("test");
        }

        int lastRowNum = sheet1.getLastRowNum();
        System.out.println(lastRowNum);

        Row row = sheet1.createRow(lastRowNum + 1);
        Cell cell = row.createCell(0);
        cell.setCellValue("测试数据");

        row = sheet1.createRow(lastRowNum + 2);
        cell = row.createCell(0);
        cell.setCellValue("测试数据");

        row = sheet1.createRow(lastRowNum + 3);
        cell = row.createCell(0);
        cell.setCellValue("测试数据");

        wb.write(fileOut);
        wb.close();
    }
}
