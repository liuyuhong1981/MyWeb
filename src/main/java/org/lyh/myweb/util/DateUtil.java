/*
 * HPE Confidential
 * Copyright © 2016 HPE, Inc.
 * 
 * Created By Han Lin  - 6/20/2016
 */
package org.lyh.myweb.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    private static Logger logger = Logger.getLogger(DateUtil.class);

    public static final String FORMAT_SIMPLE = "yyyyMMddHHmmss";

    public static final String FORMAT_PMM = "yyyy-MM-dd'T'HH:mm:ss";

    public static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS");

    public static SimpleDateFormat newFormat(String format){
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat(format, Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public static String formatDate(Date date){
        return newFormat(FORMAT_SIMPLE).format(date);
    }

    public static String formatDate(Date date, String format){
        return newFormat(format).format(date);
    }

    public static Date parseDate(String date) throws ParseException {
        return newFormat(FORMAT_SIMPLE).parse(date);
    }

    public static Date parseFullDate(String date) throws ParseException {
        return parseDate(date, FORMAT_PMM);
    }

    public static Date parseDate(String date, String format) throws ParseException {
        return newFormat(format).parse(date);
    }

    public static String transform(String dateString, String originFormat, String destFormat) throws ParseException {
        Date date = parseDate(dateString, originFormat);
        return formatDate(date, destFormat);
    }

    public static String convertPMMDate(String pmmDate){
        if (pmmDate == null || pmmDate.isEmpty()){
            return pmmDate;
        }
        try{
            if (pmmDate.contains("Z")){
                pmmDate = pmmDate.replace("Z", "UTC");
            }
            return transform(pmmDate, FORMAT_PMM, FORMAT_SIMPLE);
        }catch (ParseException e){
            logger.error("Transfer pmm date format failed : pmm date =>" + pmmDate);
        }
        return "";
    }

    public static String timestamps(){
        return timeFormat.format(new Date());
    }
}
