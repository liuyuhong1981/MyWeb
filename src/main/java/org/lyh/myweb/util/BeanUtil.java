package org.lyh.myweb.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.lyh.myweb.annotation.MyAnno;
import org.lyh.myweb.domain.AnnoTest;

/**
 * 实体、对象的util
 * 
 * @author simplife
 *
 */
public class BeanUtil {
    
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("t_id", "0001");
        map.put("t_name", "lyh");
        AnnoTest obj = new AnnoTest();
        BeanUtil.copyProperties(map, obj);

        System.out.println(obj.getId());
    }

    /**
     * 复制对象的属性值 （注：赞不支持对象关系、不能类型及日期的复制）
     * @param from
     * @param to
     * @return
     */
    public static <T> T copyProperties(Object from, T to) {
        if (from == null || to == null) {
            return to;
        }

        //取得目标对接的所有方法
        Method[] methods = to.getClass().getMethods();

        for (Method method : methods) {
            String methodName = method.getName();
            // set方法
            if (methodName.startsWith("set")) {
                String propertyName = methodName.substring(3, methodName.length());
                Class<?> setType = method.getParameterTypes()[0];
                try {
                    Method fromMethod = null;
                    if ((setType == Boolean.TYPE) || (setType == Boolean.class)) {
                        try {
                            fromMethod = from.getClass().getMethod("is" + propertyName, new Class[0]);
                        } catch (NoSuchMethodException e) {
                            fromMethod = from.getClass().getMethod("get" + propertyName, new Class[0]);
                        }
                    } else {
                        fromMethod = from.getClass().getMethod("get" + propertyName, new Class[0]);
                    }

                    Object fromValue = fromMethod.invoke(from, new Object[0]);
                    if (fromValue != null) {
                        // propertyName为空值，不进行copy
                        Class<?> getType = fromMethod.getReturnType();

                        //TODO　暂时只复制类型相同的，类型不同及日期的处理后续完善
                        if (setType == getType) {
                            method.invoke(to, new Object[] { fromValue });
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return to;
    }

    /**
     * 将Map值复制到对象属性 （注：赞不支持对象关系、不能类型及日期的复制）
     * @param from
     * @param to
     * @return
     */
    public static <T> T copyProperties(Map<String, Object> from, T to) {
        for (Field field : to.getClass().getDeclaredFields()) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof MyAnno) {

                    String tcName = ((MyAnno) annotation).name();
                    String tcValue = from.get(tcName) == null ? null : String.valueOf(from.get(tcName));

                    try {
                        if (tcValue != null) {
                            field.setAccessible(true);
                            field.set(to, tcValue);
                        } else {
                            field.setAccessible(true);
                            field.set(to, "");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return to;
    }
}
