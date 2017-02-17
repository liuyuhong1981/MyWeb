package org.lyh.myweb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.function.Predicate;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * 
 */

/**
 * @author liuyuho
 *
 */
public class MyTest {

	public static boolean flag = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		dateFormat();
	}

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

	public static void testThread3() {
		int n = 0;
		Thread t1 = new Thread(new MyThread2(n));
		t1.start();
		int n2 = 1;
		Thread t2 = new Thread(new MyThread2(n2));
		t2.start();
	}

	public static void testThread2() {
		int n = 0;
		MyThread t1 = new MyThread(n);
		t1.start();
		int n2 = 1;
		MyThread t2 = new MyThread(n2);
		t2.start();
	}

	public static void testThread1() {
		int n = 0;
		// 线程调用
		FutureTask<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
			public Boolean call() throws Exception {
				try {
					if (flag) {
						System.out.println(flag);
					}
					flag = true;
					int max = 0;
					for (int i = 0; i < 100000; i++) {
						System.out.println(n + ":" + i);
						max += i;
					}
				} catch (Exception e) {
					flag = false;
					return flag;
				}
				return flag;
			}
		});
		new Thread(future).start();
		int n2 = 1;
		// 线程调用
		FutureTask<Boolean> future2 = new FutureTask<Boolean>(new Callable<Boolean>() {
			public Boolean call() throws Exception {
				try {
					if (flag) {
						System.out.println(flag);
					}
					flag = true;
					int max = 0;
					for (int i = 0; i < 100000; i++) {
						System.out.println(n2 + ":" + i);
						max += i;
					}
				} catch (Exception e) {
					flag = false;
					return flag;
				}
				return flag;
			}
		});
		new Thread(future2).start();
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

	public static void testMathAllocation() {
		List<String> list = new ArrayList<String>();
		list.add("0.4");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");

		// 取得配额总和
		double totalRatio = 0;
		int count = 100;
		for (String s : list) {
			totalRatio = totalRatio + Double.parseDouble(s);
		}

		// 技能组A：催收员1：1.0 催收员2：1.0 催收员3：0.8 催收员4：1.2
		//
		// 如果此次跑批分配100个合同技能组A，分配规则如下：
		// 100/(1.0+1.0+0.8+1.2)=25
		// 催收员1：1.0 *25=25
		// 催收员2：1.0 *25=25
		// 催收员3：0.8 *25= 20
		// 催收员4：1.2*25=30

		double basicCountTemp = count / totalRatio;
		Map<String, Integer> staffRatioMap = new HashMap<String, Integer>();
		int basicCount = (int) Math.floor(basicCountTemp);
		int realCount = 0;
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			double howMany = basicCount * Double.parseDouble(s);
			staffRatioMap.put(String.valueOf(i), (int) Math.floor(howMany));
			realCount = realCount + (int) Math.floor(howMany);
		}

		int gap = count - realCount;
		if (gap > 0) {
			Set<String> ks = staffRatioMap.keySet();
			Iterator<String> it = ks.iterator();
			while (it.hasNext()) {
				String staffId = it.next();
				Integer howMany = staffRatioMap.get(staffId);
				howMany = howMany + 1;
				staffRatioMap.put(staffId, howMany);
				gap = gap - 1;
				if (gap == 0) {
					break;
				}
			}
		}
		System.out.println(staffRatioMap);
	}

	public static void testChangeInParameterObject(User user) {
		user.setAge(10);
		user.setName("lyh");
	}

	public static void testMathMethod() {
		// double totalRatio = 0;
		// int count = 1000;
		// double basicCountTemp = count / totalRatio;
		// int basicCount = (int) Math.floor(basicCountTemp);
		//
		// System.out.println(basicCount);
	}

	public void testCalendar() {
		// Calendar cal = Calendar.getInstance();
		// cal.set(2016, 5, 6);
		// System.out.println(cal.getTime());
		// cal.add(Calendar.DAY_OF_YEAR, 128);
		// System.out.println(cal.getTime());

		// int[] A = new int[]{-1,1};
		// System.out.println(testDemo(A));
	}

	public static int testDemo(int[] A) {
		double sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		double left = 0;
		for (int i = 0; i < A.length; i++) {
			int p = A[i];
			if (left * 2 == (sum - p)) {
				return i;
			}
			left += A[i];
		}
		return -1;
	}
}

class Test {
	Test() {
	}

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 编号
	 */
	private String id;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}

class User {
	String name;
	int age;

	User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	User() {
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
}

class MyThread extends Thread {
	private int n;

	MyThread(int n) {
		this.n = n;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(n + ":" + i);
		}
	}
}

class MyThread2 implements Runnable {
	private int n;

	MyThread2(int n) {
		this.n = n;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(n + ":" + i);
		}
	}
}

class SortObject implements Comparable<SortObject> {
	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public int compareTo(SortObject o) {
		return Integer.valueOf(this.age).compareTo(Integer.valueOf(o.getAge()));
	}
}