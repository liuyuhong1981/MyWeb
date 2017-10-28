/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import org.lyh.myweb.dto.User;

public class BasicTest {

    public static void main(String[] args) throws Exception {
    }

    public static void testFileGenerate() throws Exception {

        List<String> newLineList = new ArrayList<String>();
        List<String> newLineList2 = new ArrayList<String>();

        FileInputStream fis = new FileInputStream(
                "C:/ProjectSrc/git/data-share-exchange-2.0/develop/project/gswwEtl-parent/gswwEtl-kettle/kettle-old-lib/app_maven_install.bat");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine();
        while (line != null) {
            if (line.contains("mvn install")) {
                List<String> lineList = Arrays.asList(line.split(" "));
                List<String> nameList = Arrays.asList(lineList.get(3).split("="));
                List<String> groupList = Arrays.asList(nameList.get(1).split("-"));
                String fileName = nameList.get(1).split(".jar")[0];
                String groupName = groupList.get(0);
                line = line.replace("-DgroupId=antlr", "-DgroupId=" + groupName);
                line = line.replace("-DartifactId=antlr", "-DartifactId=" + fileName);
                line = line.replace("-Dversion=3.4", "-Dversion=1.0");
                newLineList.add(line);
                
                String depLine = "<dependency><groupId>" + groupName + "</groupId><artifactId>" + fileName + "</artifactId><version>1.0</version></dependency>";
                newLineList2.add(depLine);
            }
            line = br.readLine();
        }
        br.close();
        isr.close();
        fis.close();

        FileOutputStream fos = new FileOutputStream(
                "C:/ProjectSrc/git/data-share-exchange-2.0/develop/project/gswwEtl-parent/gswwEtl-kettle/kettle-old-lib/app_maven_install2.bat");
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);

        for (String newLine : newLineList) {
            bw.write(newLine);
            bw.newLine();
        }

        bw.close();
        osw.close();
        fos.close();

        FileOutputStream fos2 = new FileOutputStream(
                "C:/ProjectSrc/git/data-share-exchange-2.0/develop/project/gswwEtl-parent/gswwEtl-kettle/kettle-old-lib/app_maven_dependencies.xml");
        OutputStreamWriter osw2 = new OutputStreamWriter(fos2);
        BufferedWriter bw2 = new BufferedWriter(osw2);

        for (String newLine2 : newLineList2) {
            bw2.write(newLine2);
            bw2.newLine();
        }

        bw2.close();
        osw2.close();
        fos2.close();
    }
    

    public static void testRunSwingDesktopProgram() throws IOException, ClassNotFoundException, Exception {
        Robot robot = new Robot();
        String text = "Hello";
        File file = File.createTempFile("tmp", ".txt");

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().edit(file);
            Thread.sleep(1000);
        } else {
            // dunno, up to you to handle this
        }
        for (char c : text.toCharArray()) {
            int keyEvent = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyEvent);
            Thread.sleep(500);
        }
    }

    public static void testGetMethod() throws Exception {
        Method method = new BasicTest().getClass().getDeclaredMethod("testFinal", String.class);
        System.out.println(method.getName());
    }

    public static void testFinal(String arg) {
        final List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        System.out.println(list.size());
    }

    public static void testBase64() {
        String userAndPass = "lyh:12345";
        String encodeStr = Base64.getEncoder().encodeToString(userAndPass.getBytes());
        System.out.println(encodeStr);
        String newUserAndPass = new String(Base64.getDecoder().decode(encodeStr.getBytes()));
        System.out.println(newUserAndPass);
    }

    interface TestInterface {
        int value();

        TestInterface[] getValues();
    }

    public enum TestImpl implements TestInterface {
        aaa(1), bbb(2);

        public int num;

        TestImpl(int num) {
            this.num = num;
        }

        @Override
        public int value() {
            return this.num;
        }

        @Override
        public TestInterface[] getValues() {
            return TestImpl.values();
        }
    }

    public static void enumTest(Class<? extends TestInterface> clazz) throws Exception {

        if (!clazz.isEnum()) {
            throw new IllegalArgumentException();
        }
        TestInterface[] testArr = clazz.getEnumConstants();
        for (TestInterface item : testArr) {
            System.out.println(item.value());
        }
    }

    public static void asciiToString(int num) {
        System.out.println(num + " -> " + (char) num);
    }

    public static void stringToAscii(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i] + " -> " + (int) chars[i]);
        }
    }

    public enum TestEnum {
        True, False;

        public boolean getBoolean() {
            if (this.equals(TestEnum.True)) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static void testEnum(TestEnum param) {
        System.out.println(param.getBoolean());
    }

    @SuppressWarnings("deprecation")
    public static void testCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 3, 4, 0, 0, 0);
        System.out.println(sdf.format(cal.getTime()));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(sdf.format(cal.getTime()));

        System.out.println(cal.getTime());
        System.out.println(cal.getTime().getDay());

        while (true) {
            if (cal.getTime().getDay() == 1) {
                break;
            } else {
                System.out.println(cal.getTime() + " not Mondy, day + 1");
                cal.add(Calendar.DAY_OF_YEAR, 1);
            }
        }

        System.out.println("Found Monday, " + cal.getTime());

        for (int i = 0; i < 7; i++) {
            System.out.println("Day " + (i + 1) + " begin " + sdf.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_YEAR, 1);
            System.out.println("Day " + (i + 1) + " end " + sdf.format(cal.getTime()));
        }
    }

    public static void testDateTransform() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date date = sdf1.parse("16-Mar-2017 13:48");
            System.out.println(sdf2.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static void dateFormat() {
        Date date = new Date("2016-12-31 00:00:00");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
        System.out.println(sdf.format(date));
    }

    public static void lambda3() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.toString().startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.toString().length() > 4);
    }

    public static void filter(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

    public static void lambda2() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.forEach(n -> System.out.println(n));
    }

    public static void lambda1() {
        new Thread(() -> System.out.println("lambda1")).start();
    }

    public static void testChangeInParameterObject(User user) {
        user.setAge(10);
        user.setName("lyh");
    }

}
