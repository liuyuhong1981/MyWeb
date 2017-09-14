/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 HPE, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月14日<br />
 */
package org.lyh.myweb;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.lyh.myweb.domain.DomainObject;
import org.lyh.myweb.dto.User;
import org.lyh.myweb.util.DateUtil;
import org.lyh.myweb.util.XMLUtil;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;

public class TransformTest {

    public static void testXMLObjectTransform() {
        DomainObject obj = new DomainObject();
        obj.setId("aaa");
        obj.addValue("111");
        obj.addValue("222");
        String xmlString = XMLUtil.convertToXmlString(obj);
        System.out.println(xmlString);
    }

    public static void testParseExpression() {
        String expression = "[CMA]AA00 = AA01 & ([CMA]AB00 != AB01 | [GD_1]AC00 = AC01) & [GD_1]AD00 != AD01";
        System.out.println(parseExpression(expression));
    }

    public static String parseExpression(String expression) {
        List<String> splitList = new ArrayList<String>();
        List<String> speratorList = new ArrayList<String>();
        splitExpression(expression, splitList, speratorList);

        String result = "";
        for (int i = 0; i < splitList.size(); i++) {
            String splitString = splitList.get(i);
            if (i < splitList.size() - 1) {
                result += splitString.replaceAll("\\[[^,]*\\]", "") + speratorList.get(i);
            } else {
                result += splitString.replaceAll("\\[[^,]*\\]", "");
            }
        }

        return result;
    }

    public static void splitExpression(String expression, List<String> splitList, List<String> speratorList) {
        int indexOfAnd = 0;
        int indexOfOr = 0;
        if (expression.contains("&")) {
            indexOfAnd = expression.indexOf("&");
        }
        if (expression.contains("|")) {
            indexOfOr = expression.indexOf("|");
        }
        int index = 0;
        if (indexOfAnd != 0 && indexOfOr == 0) {
            index = indexOfAnd;
            speratorList.add(" & ");
        } else if (indexOfAnd == 0 && indexOfOr != 0) {
            index = indexOfOr;
            speratorList.add(" | ");
        } else if (indexOfAnd < indexOfOr) {
            index = indexOfAnd;
            speratorList.add(" & ");
        } else if (indexOfAnd > indexOfOr) {
            index = indexOfOr;
            speratorList.add(" | ");
        }

        String leftExpression = expression.substring(0, index);
        String rightExpression = expression.substring(index + 1);
        splitList.add(leftExpression.trim());

        if (rightExpression.contains("&") || rightExpression.contains("|")) {
            splitExpression(rightExpression, splitList, speratorList);
        } else {
            splitList.add(rightExpression.trim());
        }
    }

    public static Date saveConvertDateString(String dateString) throws ParseException {
        if (dateString == null || dateString.trim().equals("")) {
            return null;
        }
        return DateUtil.parseDate(dateString);
    }

    public static Date saveConvertDateStringForPMM(String dateString) throws ParseException {
        if (dateString == null || dateString.trim().equals("")) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = simpleDateFormat.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -8);
        return cal.getTime();
    }

    public static String[] getPNO18Effectivity() {
        String effectivityString = "2015-01-01..9999-12-31";
        String[] dates = null;
        try {
            if (!Strings.isNullOrEmpty(effectivityString)) {
                String[] dateRange = null;
                if (effectivityString.contains(". ")) {
                    dateRange = effectivityString.split(". ");
                } else {
                    dateRange = new String[] { effectivityString };
                }
                if (dateRange.length >= 1) {
                    if (dateRange[0].contains("Date=")) {
                        dates = dateRange[0].substring(5).split("[.][.]");
                    } else {
                        dates = dateRange[0].split("[.][.]");
                    }
                    for (int i = 0; i < dates.length; i++) {
                        if (dates[i].contains("T")) {
                            dates[i] = dates[i].substring(0, dates[i].indexOf('T'));
                        }
                    }
                    if (dates.length >= 2) {
                        if (dates[1].equalsIgnoreCase("UP") || dates[1].equalsIgnoreCase("SO") || dates[1].trim().equalsIgnoreCase("")) {
                            dates[1] = "9999-12-31";
                        }
                    } else if (dates.length == 1) {
                        dates = new String[] { dates[0], "9999-12-31" };
                    }
                }

                dates[0] = Strings.isNullOrEmpty(dates[0]) ? "" : DateUtil.transform(dates[0], "yyyy-MM-dd", "yyyyMMddHHmmss");
                dates[1] = Strings.isNullOrEmpty(dates[1]) ? "" : DateUtil.transform(dates[1], "yyyy-MM-dd", "yyyyMMddHHmmss");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dates == null) {
            dates = new String[] { "", "" };
        }
        return dates;
    }

    public static void filterBaseModelAndPNOElement(String fullString) {
        fullString = fullString.replace("(", "");
        fullString = fullString.replace(")", "");
        fullString = fullString.replace("&amp;", "&");

        String[] options = fullString.split("&");
        final List<String> optionLists = new ArrayList<String>();
        for (String option : options) {
            option = option.replaceAll("\\[[^,]*\\]", "");
            if ((!option.contains("_BASE_MODEL")) && (!option.contains("\'\'")))
                optionLists.add(option);
        }

        final List<String> filterFamilys = newArrayList(Arrays.asList("01-03", "04-05", "06-07", "08-08", "09-09", "10-10", "11-12", "13-15", "16-18"));
        List<String> resultLists = new ArrayList<String>();
        for (String optionList : optionLists) {
            if (!filterFamilys.contains((optionList.substring(0, optionList.indexOf("=") - 1).trim()))) {
                resultLists.add(optionList);
            }
        }
        String result = Joiner.on("&").join(resultLists);
        System.out.println(result);
        System.out.println(spiltOptionString(result, new ArrayList<String>()));
    }

    public static List<String> spiltOptionString(String optionString, List<String> optionStringList) {
        if ((optionString != null) && (optionString.length() > 50)) {
            String tempString = optionString.substring(0, 50);
            int tempPosition = tempString.lastIndexOf("&") + 1;
            optionStringList.add(optionString.substring(0, tempPosition));
            optionString = optionString.substring(tempPosition, optionString.length());
            spiltOptionString(optionString, optionStringList);
        } else {
            optionStringList.add(optionString);
        }
        return optionStringList;
    }

    public static void parseFullConfiguration() {
        String fullConfiguration = "(([GD_1]MA00 = MA10 &amp; [GD_1]AE00 != AE03 | [GD_1]MD00 = MD01 | [GD_1]ME00 = ME01))";
        fullConfiguration = fullConfiguration.replace("(", "");
        fullConfiguration = fullConfiguration.replace(")", "");
        fullConfiguration = fullConfiguration.replace("&amp;", "&");
        fullConfiguration = fullConfiguration.replace(" = ", "=");
        fullConfiguration = fullConfiguration.replace(" != ", "!=");

        System.out.println(fullConfiguration);

        String[] items = fullConfiguration.split(" ");
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            if (item.contains("!=")) {
                items[i] = "";
                items[i - 1] = "";
            }
        }

        String config = "";
        for (String item : items) {
            config += item;
        }

        config = config.replace("=", " = ");
        config = config.replace("&", " & ");
        config = config.replace("|", " | ");
        System.out.println(config);
    }

    public static void testReadDocumentFromXML() {
        Document doc = XMLUtil.readDocumentFromFile("c:\\Temp\\Result44.xml");
        Element root = doc.getRootElement();
        Element context = root.element("Body").element("PNO18Solve").element("SolveResult").element("Context");
        List<Element> list = context.elements("PNO18");
        for (Element element : list) {
            System.out.println("--------------------------------------");
            System.out.println(element.elementText("ID"));
            System.out.println(element.elementText("SolveDate"));
            System.out.println(element.elementText("OptionString"));
        }
    }

    public static void testReplaceAllForXMLDocument() throws Exception {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ExpressionSplit><ExpressionList>CB00 = CB01 &amp; BD00 = BD03</ExpressionList></ExpressionSplit>";
        xmlString = xmlString.replaceAll("&amp;", "&");
        xmlString = xmlString.replaceAll("<ExpressionList>", "<![CDATA[");
        xmlString = xmlString.replaceAll("</ExpressionList>", "]]>");
        System.out.println(xmlString);
        Document document = DocumentHelper.parseText(xmlString);
        xmlString = document.asXML();
        System.out.println(xmlString);
    }

    public static void testRuleParse() {
        String expression = "(([GD_1]MA00 = MA10 &amp; [GD_1]AE00 != AE03 | [GD_1]MD00 = MD01 | [GD_1]ME00 = ME01))";
        expression = expression.replaceAll("&amp;", "&");
        expression = expression.replaceAll("\\[GD_1\\]", "");
        expression = expression.replaceAll("\\(", "");
        expression = expression.replaceAll("\\)", "");
        System.out.println(expression);
    }

    public static void testEffectivityParse(String effectivityString) {
        String[] effectivity = new String[] { "", "" };
        try {
            if (!Strings.isNullOrEmpty(effectivityString)) {
                effectivityString = effectivityString.trim();
                effectivityString = effectivityString.replace("[Teamcenter::]", "");
                effectivityString = effectivityString.replace("Date", "");
                effectivityString = effectivityString.replace(">=", "");
                effectivityString = effectivityString.replace("<", "");
                String[] effectivityArray = effectivityString.split("&");
                if (effectivityArray.length > 1) {
                    effectivity[0] = effectivityArray[0];
                    effectivity[1] = effectivityArray[1];
                } else if (effectivityArray.length == 1) {
                    effectivity[0] = effectivityArray[0];
                    effectivity[1] = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String effIn = effectivity[0];
        String effOut = effectivity[1];

        System.out.println("EffIn: " + effIn + ", effOut: " + effOut);
    }

    public static void testDomParse() {
        User user = new User();
        user.setAge(11);
        user.setName("aaa");
        String xmlString = XMLUtil.convertToXmlString(user);
        System.out.println(xmlString);

        xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"><user><age>22</age><name>bbb</name></user>";
        if (xmlString.contains("<?xml")) {
            int index = xmlString.indexOf(">");
            xmlString = xmlString.substring(index + 1);
            System.out.println(xmlString);
        }
        User user2 = (User) XMLUtil.convertXmlStrToObject(User.class, xmlString);
        System.out.println(user2.getAge());
    }

    public static void testTransformWithNull() {
        FluentIterable<User> fi = getAvailableOptionValuesByFamilyIntent("", null, new ClassCastFunction<User, User>() {
            @Override
            public User build(User rule, User optionValue) {
                if (optionValue == null) {
                    return null;
                }
                return new User(optionValue.getName(), optionValue.getAge());
            }
        });

        List<User> userList = fi.filter(Predicates.notNull()).toList();

        System.out.println(userList.size());
        for (User user : userList) {
            System.out.println(user == null || user.getName() == null ? "null" : user.getName());
        }
    }

    public static <T extends User> FluentIterable<T> getAvailableOptionValuesByFamilyIntent(String familyIntent, String type,
            final ClassCastFunction<T, User> function) {
        final List<User> optionValues = new ArrayList<User>();
        User u1 = new User();
        u1.setAge(10);
        u1.setName("aaa");
        User u2 = new User();
        u2.setAge(11);
        u2.setName("bbb");
        optionValues.add(u1);
        optionValues.add(u2);

        List<User> availabilityRules = new ArrayList<User>();
        availabilityRules.addAll(optionValues);
        return from(availabilityRules).filter(new com.google.common.base.Predicate<User>() {
            @Override
            public boolean apply(User rule) {
                return rule.getName() != null;
            }
        }).transform(new Function<User, T>() {
            @Override
            public T apply(User rule) {
                if (rule.getAge() == 10) {
                    return null;
                }
                return function.build(rule, rule);
            }
        });
    }

    public static List<String> splitExpression(String expression, int splitNumber) {
        // AA00=AA01 BB00=BB01 CC00=CC01 DD00=DD01 EE00=EE01 FF00=FF01 GG00=GG01
        // HH00=HH01 II00=II01 JJ00=JJ01
        List<String> expressionList = new ArrayList<String>();
        boolean flag = true;
        int splitNum = splitNumber;
        if (expression == null || expression.length() <= splitNum) {
            flag = false;
            expressionList.add(expression);
        } else {
            while (flag) {
                String splitChar = expression.substring(splitNum - 1, splitNum);
                if (" ".equals(splitChar)) {
                    String splitPart = expression.substring(0, splitNum);
                    expressionList.add(splitPart.trim());
                    expression = expression.substring(splitNum, expression.length());
                    splitNum = splitNumber;
                    System.out.println(expression);
                } else {
                    splitNum--;
                }
                if (splitNum == 0) {
                    flag = false;
                }
                if (expression.length() <= splitNum) {
                    flag = false;
                    expressionList.add(expression);
                }
            }
        }

        return expressionList;
    }

    static Map<String, Integer> parseExpressionToOptionAndCount(String expression, Map<String, Integer> keyValues) {
        if (Strings.isNullOrEmpty(expression)) {
            return null;
        }

        if (keyValues == null || keyValues.isEmpty()) {
            keyValues = new HashMap<String, Integer>();
        }

        // remove ( & )
        expression = expression.replaceAll("\\(", "");
        expression = expression.replaceAll("\\)", "");
        expression = expression.replaceAll("AND", "@@");
        expression = expression.replaceAll("&", "@@");
        expression = expression.replaceAll("OR", "@@");
        expression = expression.replaceAll("[|]", "@@");

        String[] options = expression.split("@@");

        for (String option : options) {
            String result = option.replaceAll("\\[[^,]*\\]", "");
            String[] values = result.split("=");

            if (values.length == 2) {
                String key = values[1].trim();
                if (keyValues.containsKey(key)) {
                    keyValues.put(key, keyValues.get(key) + 1);
                } else {
                    keyValues.put(key, 1);
                }
            }
        }
        return keyValues;
    }

    static Map<String, List<String>> parseOptionExpression(String expression) {
        if (Strings.isNullOrEmpty(expression)) {
            return null;
        }

        Map<String, List<String>> keyValues = new HashMap<String, List<String>>();

        // remove ( & )
        expression = expression.replaceAll("\\(", "");
        expression = expression.replaceAll("\\)", "");
        expression = expression.replaceAll("AND", "@@");
        expression = expression.replaceAll("&", "@@");
        expression = expression.replaceAll("OR", "@@");
        expression = expression.replaceAll("[|]", "@@");

        String[] options = expression.split("@@");

        for (String option : options) {
            String result = option.replaceAll("\\[[^,]*\\]", "");
            String[] values = result.split("=");

            if (values.length == 2) {
                String key = values[0].trim();
                if (keyValues.containsKey(key)) {
                    keyValues.get(key).add(values[1].trim());
                } else {
                    keyValues.put(values[0].trim(), newArrayList(values[1].trim()));
                }
            }
        }
        return keyValues;
    }

}
