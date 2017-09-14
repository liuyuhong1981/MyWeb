package org.lyh.myweb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lyh.myweb.dto.SortObject;
import org.lyh.myweb.dto.Test;
import org.lyh.myweb.dto.User;

/**
 * 
 */

/**
 * @author liuyuho
 *
 */
public class CollectionTest {

    public static void testLoop() {
        Boolean isOK = true;
        for (int i = 0; i < 10 && isOK; i++) {
            for (int j = 0; j < 5 && isOK; j++) {
                if (i == 3 && j == 4) {
                    System.out.println("i == " + i + ", j == " + j);
                    isOK = false;
                }
            }
            System.out.println(i);
        }
    }

    public static void testCollectionOperation() {
        Map<String, String> idMap = new HashMap<String, String>();
        idMap.put("1", "aaa");
        idMap.put("2", "bbb");
        idMap.put("3", "ccc");
        List<String> keyList = new ArrayList<String>(idMap.keySet());
        keyList.remove("2");
        System.out.println(keyList);
    }

    public static void testObjectInListAndMap() {
        User user = new User();
        user.setAge(10);
        user.setName("aaa");
        List<User> list = new ArrayList<User>();
        Map<String, User> map = new HashMap<String, User>();
        list.add(user);
        map.put(user.getName(), user);

        map.get("aaa").setAge(11);
        System.out.println(list.get(0).getAge());
    }

    public static void testSort() {
        List<SortObject> list = new ArrayList<SortObject>();
        SortObject obj = new SortObject();
        obj.setAge("11");
        obj.setName("bba");
        list.add(obj);

        obj = new SortObject();
        obj.setAge("12");
        obj.setName("aab");
        list.add(obj);

        obj = new SortObject();
        obj.setAge("13");
        obj.setName("abb");
        list.add(obj);

        obj = new SortObject();
        obj.setAge("23");
        obj.setName("eee");
        list.add(obj);

        obj = new SortObject();
        obj.setAge("22");
        obj.setName("hhh");
        list.add(obj);

        obj = new SortObject();
        obj.setAge("21");
        obj.setName("aaa");
        list.add(obj);

        obj = new SortObject();
        obj.setAge("1");
        obj.setName("ddd");
        list.add(obj);

        obj = new SortObject();
        obj.setAge("3");
        obj.setName("ccc");
        list.add(obj);

        obj = new SortObject();
        obj.setAge("2");
        obj.setName("bbb");
        list.add(obj);

        System.out.println("----------------------------------- 排序前");
        for (SortObject o : list) {
            System.out.println(o.getAge() + " | " + o.getName());
        }

        Collections.sort(list, null);
        System.out.println("----------------------------------- 排序后");
        for (SortObject o : list) {
            System.out.println(o.getAge() + " | " + o.getName());
        }
    }

    public static void testUpdateInList() {
        List<Test> list = new ArrayList<Test>();
        Test t1 = new Test();
        t1.setId("1");
        t1.setName("a");
        list.add(t1);
        Test t2 = new Test();
        t2.setId("2");
        t2.setName("b");
        list.add(t2);
        Test t3 = new Test();
        t3.setId("3");
        t3.setName("c");
        list.add(t3);

        for (Test test : list) {
            test.setId(test.getId() + "..");
        }

        for (Test test : list) {
            System.out.println(test.getId());
        }
    }

    public static void testStartAndEndIndex() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("g1", 11);
        map.put("g2", 1);
        map.put("g3", 1);

        List<String> gList = new ArrayList<String>();
        gList.add("1");
        gList.add("2");
        gList.add("3");
        gList.add("4");
        gList.add("5");
        gList.add("6");
        gList.add("7");
        gList.add("8");
        gList.add("9");
        gList.add("10");
        gList.add("11");
        gList.add("12");
        gList.add("13");

        List<String> rList = new ArrayList<String>();

        Set<String> ks = map.keySet();
        Iterator<String> it = ks.iterator();
        int indexStart = 0;
        int indexEnd = 0;
        while (it.hasNext()) {
            String ooId = it.next();
            indexEnd = indexStart + map.get(ooId);

            for (int i = indexStart; i < indexEnd; i++) {
                rList.add(ooId);
            }

            indexStart = indexEnd;
        }

        System.out.println(rList);
    }

}